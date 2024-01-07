package com.yang.yangapiclientsdk;

import org.springframework.util.DigestUtils;

public class demo {
    public static void main(String[] args) {
        String s = DigestUtils.md5DigestAsHex("884039224".getBytes());
        String ss = DigestUtils.md5DigestAsHex("884039224".getBytes());
        boolean equals = s.equals(ss);
        System.out.println(equals);
    }
}
