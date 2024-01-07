package com.yangapi.yangapiinterface;

import com.yang.yangapiclientsdk.client.NameClient;
import com.yang.yangapiclientsdk.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YangapiInterfaceApplicationTests {

    @Autowired
    NameClient nameClient;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUserName("yxs");
        nameClient.getPostName(user);
    }

}
