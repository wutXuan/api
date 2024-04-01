package com.api.apiinterface.controller;

import com.api.apiclientsdk.model.User;
import com.api.apiclientsdk.utils.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/name")
public class NameController {

    @PostMapping ("/getNameByPost")
    public String getUserBodyByPost(@RequestBody User user, HttpServletRequest request){
//        String accessKey = request.getHeader("accessKey");
//        String nonce = request.getHeader("noce");
//        String timesamp = request.getHeader("timestamp");
//        String sign = request.getHeader("sign");
//        String body = request.getHeader("body");
////        String secretKey = request.getHeader("secretKey");
//        //根据accessKey到数据库中查询secretKey得到：
//        String secretKey = "abcd";
//        String rightSign = StringUtils.getSign(body,secretKey);
//
//        if(!accessKey.equals("xuanxuan")){
//            throw new RuntimeException("无权限1");
//        }

//        if(Long.parseLong(nonce) > 10000){
//            throw new RuntimeException("禁止重放");
//        }

//        if(!rightSign.equals(sign)){
//            throw new RuntimeException("无权限2");
//        }
        return "Post 用户的名字是" + user.getUsername();
    }
}
