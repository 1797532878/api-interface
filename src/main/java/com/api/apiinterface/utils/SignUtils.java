package com.api.apiinterface.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.Map;

/**
 * 加密工具
 */
public class SignUtils {

    public static String genSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body.toString() + "." + secretKey;
        return md5.digestHex(content);
    }

}
