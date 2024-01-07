package com.yangapi.yangapigateway;

import entity.InterfaceInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest

class YangapiGatewayApplicationTest {
//    @DubboReference
//    InterfaceInfoService interfaceInfoService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

//    @Test
//    public void myTest() {
//        InterfaceInfo result = interfaceInfoService.getbyInterfaceInfoId(Long.valueOf(1));
//        System.out.println("Receive result ======> " + result);
//    }

    @Test
    public void redisTest(){
        stringRedisTemplate.opsForValue().set("demo","demo");
    }
}