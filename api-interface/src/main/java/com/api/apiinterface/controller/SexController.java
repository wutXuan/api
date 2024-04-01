package com.api.apiinterface.controller;

import com.api.apiclientsdk.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sex")
public class SexController {

    @PostMapping("/getSexByPost")
    public String getUserBodyByPost(@RequestBody User user, HttpServletRequest request){
        String sex = user.getSex();
        if(sex.equals("0")){
            sex = "性别:男";
        }else if(sex.equals("1")){
            sex = "性别:女";
        }else{
            sex = "非法!!";
        }
        return sex;
    }
}
