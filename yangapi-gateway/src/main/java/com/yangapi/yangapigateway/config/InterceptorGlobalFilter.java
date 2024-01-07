package com.yangapi.yangapigateway.config;

import cn.hutool.core.util.ObjectUtil;
import com.yangapi.yangapigateway.utils.SignUtils;
import entity.InterfaceInfo;
import entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.InnerInterfaceInfoService;
import service.InnerUserService;


import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Slf4j
@Component
public class InterceptorGlobalFilter implements GlobalFilter, Ordered {

    public static final String GATEWAYKEY="gateway";

    public static final String GATEWAYVALUE="springcloud";

    public static final String REDISSETKEY="random";

    private static final String INTERFACE_HOST = "http://localhost:8141";

    @DubboReference
    InnerInterfaceInfoService interfaceInfoService;

    @DubboReference
    InnerUserService userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    /***
     * 需实现功能：
     * 统一鉴权、统一业务处理、访问控制、流量染色、统一日志
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        URI uri = request.getURI();
        String host = uri.getHost();
        String path = INTERFACE_HOST + request.getPath().value();
        String method = request.getMethod().name();
        String query = uri.getQuery();
        String rawPath = uri.getRawPath();
        String scheme = uri.getScheme();
        String id = request.getId();
        InetSocketAddress localAddress = request.getLocalAddress();
        log.info("请求唯一标识：" + request.getId());
        log.info("请求路径：" + path);
        log.info("请求方法：" + method);
        log.info("请求参数：" + request.getQueryParams());
        String sourceAddress = request.getLocalAddress().getHostString();
        log.info("请求来源地址：" + sourceAddress);
        log.info("请求来源地址：" + request.getRemoteAddress());
        // 1.统一鉴权
        try {
            verify(exchange);
        } catch (Exception e) {
            return handleNoAuto(response);
        }
        // 2.流量染色
        request.mutate()
                .header(GATEWAYKEY,GATEWAYVALUE)
                .build();
        // 3.统一业务处理
        // 3.1 通过http/rpc 远程调用判断是否有这个接口,通过接口名称和请求方式
        // 把接口地址和请求方式拿去校验
        InterfaceInfo interfaceInfo = interfaceInfoService.verifyInterfaceHave(path, method);
        if (ObjectUtil.isEmpty(interfaceInfo)){
            log.info("接口地址和请求方式校验失败");
            return handleNoAuto(exchange.getResponse());
        }
        // 3.2 调用接口
//        return handleResponse(exchange, chain);
        return chain.filter(exchange);
        // 4. 访问控制,暂时存放在final静态集合中，后续需要数据库持久化
    }

    /**
     * 处理响应
     *
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            // 缓存数据的工厂
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // todo 响应码403
            HttpStatus statusCode = originalResponse.getStatusCode();
            log.info(statusCode.toString());
            if (statusCode == HttpStatus.FORBIDDEN) {
                // 装饰，增强能力
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    // 等调用完转发的接口后才会执行
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            // 往返回值里写数据
                            // 拼接字符串
                            return super.writeWith(
                                    fluxBody.map(dataBuffer -> {
                                        byte[] content = new byte[dataBuffer.readableByteCount()];
                                        dataBuffer.read(content);
                                        DataBufferUtils.release(dataBuffer);//释放掉内存
                                        // 构建日志
                                        StringBuilder sb2 = new StringBuilder(200);
                                        List<Object> rspArgs = new ArrayList<>();
                                        rspArgs.add(originalResponse.getStatusCode());
                                        String data = new String(content, StandardCharsets.UTF_8); //data
                                        sb2.append(data);
                                        // 打印日志
                                        log.info("响应结果：" + data);
                                        return bufferFactory.wrap(content);
                                    }));
                        } else {
                            // 8. 调用失败，返回一个规范的错误码
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                // 设置 response 对象为装饰过的
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange); // 降级处理返回数据
        } catch (Exception e) {
            log.error("网关处理响应异常" + e);
            return chain.filter(exchange);
        }
    }




    public void verify(ServerWebExchange exchange){
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = request.getHeaders();
        String reqAccessKey = headers.get("accessKey").get(0);
        String body = headers.get("body").get(0);
        String sign = headers.get("sign").get(0);
        String nonce = headers.get("nonce").get(0);
        String timestamp = headers.get("timestamp").get(0);
        String random = headers.get("random").get(0);
        // 通过username获取该user信息
        User user = userService.getSigntoDataSource(body);
        if (ObjectUtil.isEmpty(user)){
            handleNoAuto(response);
        }
        String userName = user.getUserName();
        String accessKey = user.getAccessKey();
        String secretKey = user.getSecretKey();

        // 查询数据库这个accessKey是否分配给用户
        if (!reqAccessKey.equals(accessKey)){
            log.info("accesskey question");
            throw new RuntimeException("accesskey校验失败");
        }
        // 校验时间戳
        Long aTimeStamp = Long.valueOf(timestamp)+ 10 * 60 * 1000;
        long currentTime = System.currentTimeMillis();  // 获取当前时间的毫秒值
        if (aTimeStamp>currentTime){
            log.info("timestamp question");
            throw new RuntimeException("timestamp校验失败");

        }
        // 校验随机数（防止重放),后面实现，可使用redis
        Set<String> members = stringRedisTemplate.opsForSet().members(REDISSETKEY);
        if (ObjectUtil.isEmpty(members)){
            stringRedisTemplate.opsForSet().add(REDISSETKEY,random);
        }else {
            boolean contains = members.contains(random);
            if (!contains){
                stringRedisTemplate.opsForSet().add(REDISSETKEY,random);
            }else {
                log.info("random question");
                throw new RuntimeException("random校验失败");
            }
        }
        // 校验sign签名
        if (!sign.equals(SignUtils.generationSign(userName,secretKey))){
            log.info("sign question");
            throw new RuntimeException("sign校验失败");
        }
    }

    public Mono<Void> handleNoAuto(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
