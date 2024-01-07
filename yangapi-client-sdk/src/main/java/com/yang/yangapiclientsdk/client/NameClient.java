package com.yang.yangapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.yang.yangapiclientsdk.utils.SignUtils;
import entity.User;

import java.util.HashMap;
import java.util.Map;

/***
 * 模拟开发调用第三方接口客户端
 */
public class NameClient {

    private String accessKey;

    private String secretKey;

    private static String url="http://localhost:8090/api/name/post";


    public NameClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /***
     * body为username
     * @param user
     * @return
     */
    public Map<String,String> getHeaderMap(User user){
        String jsonStr = JSONUtil.toJsonStr(user);
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("accessKey", accessKey);
        paramMap.put("body", jsonStr);
        paramMap.put("sign", SignUtils.generationSign(user.getUserName(),secretKey));
        paramMap.put("nonce", RandomUtil.randomNumbers(3));
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
        //防止重放
        paramMap.put("random",RandomUtil.randomNumbers(5));
        return paramMap;
    }


    /**
     * accessKey secretKey
     * @param name
     */
    public void getName(String name) {
//        String result= HttpUtil.get(url, paramMap);
    }

        public String getPostName(User user) {
            String jsonStr = JSONUtil.toJsonStr(user);
            HttpResponse execute = HttpRequest.post(url)
                    .addHeaders(getHeaderMap(user))
                    .body(jsonStr)
                    .execute();
            String body = execute.body();
            return body;
        }

    public static void main(String[] args) {
        NameClient nameClient = new NameClient("yang","abcdefgh");
        User user = new User();
        user.setUserName("yangxiaosheng");
        String postName = nameClient.getPostName(user);
        System.out.println(postName);
    }

    }
