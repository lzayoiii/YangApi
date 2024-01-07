package com.yangapi.yangapiinterface.controller;

import com.yang.yangapiclientsdk.entity.User;
import com.yang.yangapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/name")
public class NameController {


    @GetMapping("/get")
    public String getName(@RequestParam String name){
        System.out.println("接收到值:"+name);
        return "this is my name";
    }

    @PostMapping("/post")
    public String getPostName(@RequestBody User user, HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        String body = request.getHeader("body");
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
//        //todo accessKey,查询数据库这个accessKey是否分配给用户
//        if (!accessKey.equals("yang")){
//            throw new RuntimeException("无权限");
//        }
//        //todo 校验时间戳
//        Long aTimeStamp = Long.valueOf(timestamp)+ 10 * 60 * 1000;
//        long currentTime = System.currentTimeMillis();  // 获取当前时间的毫秒值
//        if (aTimeStamp>currentTime){
//            throw new RuntimeException("超时");
//        }
//        //todo 校验随机数,后面实现，可使用redis
//
//        //todo 校验签名是否正确，实际情况是从数据库中查
//        if (!sign.equals(SignUtils.generationSign(body,"abcdefg"))){
//            throw new RuntimeException("无权限");
//        }
        System.out.println("调用成功 at:"+System.currentTimeMillis());
        return "user";
    }


}
