package com.api.apiinterface.controller;

import com.api.apiinterface.model.User;
import com.api.apiinterface.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * 名称API
 */
@RestController()
@RequestMapping("/name")
public class NameController {

    @GetMapping("/")
    public String getNameByGet(String name) {
        return "GET YOUR NAME IS " + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam String name) {
        return "POST YOUR NAME IS " + name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) throws UnsupportedEncodingException {

        String accessKey = new String(request.getHeader("accessKey").getBytes("ISO-8859-1"),StandardCharsets.UTF_8);
        String nonce = request.getHeader("nonce");
        String body = request.getHeader("body");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");


        // todo 实际需要去数据库中查是否分配给了用户
        if (!accessKey.equals("陈")) {
            throw new RuntimeException("无权限！");
        }
        if (Long.parseLong(nonce) > 1000) {
            throw new RuntimeException("无权限！");
        }

        // todo 查到secretKey
        String serverSign = SignUtils.genSign(body, "asdasdasd");

        if (!serverSign.equals(sign)) {
            throw new RuntimeException("无权限！");
        }

        // todo 时间不能超过当前时间相邻5分钟
        return "POST YOUR NAME IS " + user.getName();
    }

}