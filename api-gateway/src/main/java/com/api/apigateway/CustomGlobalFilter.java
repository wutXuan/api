package com.api.apigateway;


import com.api.apiclientsdk.utils.StringUtils;
import com.api.apicommon.model.entity.InterfaceInfo;
import com.api.apicommon.model.entity.User;
import com.api.apicommon.service.InnerInterfaceInfoService;
import com.api.apicommon.service.InnerUserInterfaceInfoService;
import com.api.apicommon.service.InnerUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @DubboReference
    private InnerInterfaceInfoService innerInterfaceInfoService;

    @DubboReference
    private InnerUserService innerUserService;

    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1");

    private static final String INTERFACE_HOST = "http://localhost:8123";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("custom global filter");
        ServerHttpRequest request = exchange.getRequest();
        String sourceAddress = request.getLocalAddress().getHostString();
        log.info("请求地址:" + sourceAddress);

        // 1. 拿到响应对象
        ServerHttpResponse response = exchange.getResponse();

        // 2. 白名单，放行
        if(!IP_WHITE_LIST.contains(sourceAddress)){
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }

        // 3. 用户鉴权
        HttpHeaders headers = request.getHeaders();
        String method = request.getMethod().toString();
        String path = INTERFACE_HOST + request.getPath().value();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String body = headers.getFirst("body");

        User invokeUser = null;
        try{
            invokeUser = innerUserService.getInvokeUser(accessKey);
        }catch (Exception e){
            log.error("获取用户异常");
        }

        if(invokeUser == null){
            return handleNoAuth(response);
        }



        Long currentTime = System.currentTimeMillis() / 1000;
        final Long FIVE_MINUTES = 60 * 5L;
        if(currentTime - Long.parseLong(timestamp) > FIVE_MINUTES){
            return handleNoAuth(response);
        }

        // 从数据库中查询密钥
        String secretKey = invokeUser.getSecretKey();
        String rightSign = StringUtils.getSign(body,secretKey);
        if(rightSign == null || !rightSign.equals(sign)){
            return handleNoAuth(response);
        }

        // 4. 查数据库，验证接口是否存在
        InterfaceInfo interfaceInfo = null;
        try{
            interfaceInfo = innerInterfaceInfoService.getInterfaceInfo(path, method);
        }catch (Exception e){
            log.error("获取接口异常");
        }
        if(interfaceInfo == null){
            return handleNoAuth(response);
        }

        // 5. 继续执行，请求转发
//        Mono<Void> filter = chain.filter(exchange);
//        log.info("响应为:" + response.getStatusCode());
//        if(response.getStatusCode() == HttpStatus.OK){
//
//        }else{
//            return handleInvokeError(response);
//        }
        return handleResponse(exchange,chain,interfaceInfo.getId(),invokeUser.getId());

        // 6. 调用接口次数+1

//        return filter;
    }

    public Mono<Void> handleNoAuth(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    public Mono<Void> handleInvokeError(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return -2;
    }



    public Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain,long interfaceInfoId,long invokeUserId){
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            HttpStatus statusCode = originalResponse.getStatusCode();



            if (statusCode != HttpStatus.OK) {
                return chain.filter(exchange);//降级处理返回数据
            }
            ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                @Override
                public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                    if (body instanceof Flux) {
                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);

                        return super.writeWith(fluxBody.map(dataBuffers -> {
                            byte[] content = new byte[dataBuffers.readableByteCount()];
                            dataBuffers.read(content);
                            DataBufferUtils.release(dataBuffers);//释放掉内存

                            // 构建返回日志
                            String data = new String(content, StandardCharsets.UTF_8);
                            log.info(data);

                            try{
                                boolean b = innerUserInterfaceInfoService.invokeCount(invokeUserId, interfaceInfoId);
                            }catch (Exception e){
                                log.error("invokeCount异常");
                            }


                            return bufferFactory.wrap(content);
                        }));
                    } else {
                        log.error("<-- {} 响应code异常", getStatusCode());
                    }
                    return super.writeWith(body);
                }
            };
            return chain.filter(exchange.mutate().response(decoratedResponse).build());
        } catch (Exception e) {
            log.error("网关处理异常\n" + e);
            return chain.filter(exchange);
        }

    }
}