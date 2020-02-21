package com.www.config;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClient02Config {
    /**
     * 用于创建用户名和密码的对象
     * @return
     */
//    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
//        return new BasicAuthRequestInterceptor("user","www123");
//    }

    /**
     * 配置要输出的日志有哪些，必须在debug模式下才能输出
     * @return
     */
    @Bean
    Logger.Level feignLoggerLervl(){
        return Logger.Level.FULL;
    }

}
