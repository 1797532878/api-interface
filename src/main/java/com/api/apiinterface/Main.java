package com.api.apiinterface;

import com.api.apiinterface.client.CApiClient;
import com.api.apiinterface.model.User;

public class Main {
    public static void main(String[] args) {
        String accessKey = "陈";
        String secretKey = "asdasdasd";

        CApiClient client = new CApiClient(accessKey,secretKey);
        String cc1 = client.getNameByGet("cc1");
        String cc2 = client.getNameByPost("cc2");
        User user = new User();
        user.setName("cc3");
        String userNameByPost = client.getUserNameByPost(user);
    }
}
