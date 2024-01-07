package com.yupi.project.service;

import entity.InterfaceInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class InterfaceInfoServiceTest {
    @Autowired
    InterfaceInfoService interfaceInfoService;

    @Test
    void testInterface(){
        List<InterfaceInfo> list = interfaceInfoService.list();
        for (InterfaceInfo s : list) {
            System.out.println(s);
        }
    }
}