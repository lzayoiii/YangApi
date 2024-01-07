package com.yang.yangapiclientsdk;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.yang.yangapiclientsdk.client.NameClient;
import com.yang.yangapiclientsdk.utils.SignUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/***
 * 模拟开发调用第三方接口客户端
 */
@Configuration
@ConfigurationProperties(prefix = "yangsign")
@Data
public class YangApiConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public NameClient getNameClient(){
        NameClient nameClient = new NameClient(accessKey, secretKey);
        return nameClient;
    }
    }
