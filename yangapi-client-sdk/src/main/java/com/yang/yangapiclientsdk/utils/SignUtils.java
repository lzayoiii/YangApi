package com.yang.yangapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtils {

    public static String generationSign(String name,String secretKey){
        String s = name + "/" + secretKey;
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(s);
        return digestHex;
    }
}
