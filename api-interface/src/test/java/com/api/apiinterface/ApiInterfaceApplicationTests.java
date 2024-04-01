package com.api.apiinterface;

import com.api.apiclientsdk.client.ApiClient;
import com.api.apiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiInterfaceApplicationTests {

    @Resource
    private ApiClient apiClient;

    @Test
    void contextLoads(){
        String result = apiClient.getNameByGet("xuan");
        System.out.println("result=" + result);
        User user = new User();
        user.setUsername("ssss");
        String result2 = apiClient.getUserBodyByPost(user);
        System.out.println(result2);
    }


}
