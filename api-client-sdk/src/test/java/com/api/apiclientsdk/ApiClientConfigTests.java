package com.api.apiclientsdk;

import com.api.apiclientsdk.client.ApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;

@SpringBootTest(classes = ApiClientConfigTests.class)
public class ApiClientConfigTests {

    @Resource
    private ApiClient apiClient;

    @Test
    void tttt(){
        System.out.println(11);
    }
}
