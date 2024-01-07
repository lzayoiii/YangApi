package com.yupi.project;

import com.yupi.project.service.UserInterfaceInfoService;
import com.yupi.project.service.impl.InterfaceInfoServiceImpl;
import com.yupi.project.service.impl.UserServiceImpl;
import entity.InterfaceInfo;
import entity.User;
import entity.UserInterfaceInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyApplicationTest {

    @Autowired
    InterfaceInfoServiceImpl service;

    @Autowired
    UserInterfaceInfoService userInterfaceInfoService;

    @Autowired
    UserServiceImpl userService;


    @Test
    void test(){
        InterfaceInfo byId = service.getById(1L);

        System.out.println(byId);
    }

    @Test
    void test2(){
//        List<UserInterfaceInfo> userInterfaceInfos =
//                mapper.listAll();
//        for (UserInterfaceInfo userInterfaceInfo : userInterfaceInfos) {
//
//            System.out.println("userInterfaceInfo = " + userInterfaceInfo);
//        }
        UserInterfaceInfo byId = userInterfaceInfoService.getById(1l);
        System.out.println(byId);
    }

    @Test
    void test3(){
        User byId = userService.getById(1L);
        System.out.println(byId);
    }


}