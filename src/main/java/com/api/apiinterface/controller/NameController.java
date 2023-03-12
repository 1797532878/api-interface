package com.api.apiinterface.controller;

import com.api.api_client_sdk.model.User;
import com.api.api_client_sdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 名称API
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(String name, HttpServletRequest request) {
        System.out.println(request.getHeader("chenx"));
        return "GET YOUR NAME IS " + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name) {
        return "POST YOUR NAME IS " + name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) throws UnsupportedEncodingException {

        String accessKey = new String(request.getHeader("accessKey").getBytes("ISO-8859-1"),StandardCharsets.UTF_8);
        String nonce = request.getHeader("nonce");
        String body = new String(request.getHeader("body").getBytes("ISO-8859-1"),StandardCharsets.UTF_8);
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");


        // todo 实际需要去数据库中查是否分配给了用户
        if (!accessKey.equals("asd")) {
            throw new RuntimeException("无权限！");
        }
        if (Long.parseLong(nonce) > 1000) {
            throw new RuntimeException("无权限！");
        }

        // todo 数据库查到secretKey
        String serverSign = SignUtils.genSign(body, "asdasdasd");

        if (!serverSign.equals(sign)) {
            throw new RuntimeException("无权限！");
        }

        // todo 时间不能超过当前时间相邻5分钟
        return "POST YOUR NAME IS " + user.getName();
    }

}
