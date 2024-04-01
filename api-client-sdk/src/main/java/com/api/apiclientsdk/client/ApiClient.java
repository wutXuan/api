package com.api.apiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.api.apiclientsdk.model.User;
import com.api.apiclientsdk.utils.StringUtils;


import java.util.HashMap;
import java.util.Map;


public class ApiClient {

    private String accessKey;

    private String secretKey;

    private static final String GATEWAY_HOST = "http://localhost:8091";

    public ApiClient() {
    }

    public ApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    private Map<String,String> getHeaderMap(String body){
        Map<String,String > map = new HashMap<>();
        map.put("accessKey",accessKey);
//        map.put("secretKey",secretKey);
        map.put("nonce", RandomUtil.randomNumbers(4));
        map.put("body",body);
        map.put("timestamp",String.valueOf(System.currentTimeMillis() / 1000));
        map.put("sign", StringUtils.getSign(body,secretKey));

        return map;
    }

    public String getUserBodyByPost(User user){
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/name/getNameByPost")
                .addHeaders(getHeaderMap(json)).body(json).execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }

    public String getUserSexByPost(User user){
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/sex/getSexByPost")
                .addHeaders(getHeaderMap(json)).body(json).execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }
}
