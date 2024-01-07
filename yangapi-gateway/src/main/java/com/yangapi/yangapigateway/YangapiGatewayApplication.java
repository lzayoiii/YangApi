package com.yangapi.yangapigateway;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
@EnableDubbo
public class YangapiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(YangapiGatewayApplication.class, args);
    }

}
