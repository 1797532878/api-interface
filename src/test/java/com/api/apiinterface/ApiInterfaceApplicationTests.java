package com.api.apiinterface;

import com.api.api_client_sdk.client.CApiClient;
import com.api.api_client_sdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiInterfaceApplicationTests {

    @Resource
    private CApiClient cClient;

    @Test
    void contextLoads() {
        String res = cClient.getNameByGet("ccc");
        User user =  new User();
        user.setName("chenchen");
        String nameByPost = cClient.getUserNameByPost(user);
        System.out.println(res);
        System.out.println(nameByPost);
    }

}
