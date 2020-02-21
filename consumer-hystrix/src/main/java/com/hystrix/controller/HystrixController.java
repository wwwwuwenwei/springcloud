package com.hystrix.controller;

import com.hystrix.pojo.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class HystrixController {

    @Autowired
    RestTemplate restTemplate;//spring 提供的一个用于访问 Rest 接口的模板对象

    @Qualifier("erekaClient")
    private EurekaClient erekaClient;

    @HystrixCommand(fallbackMethod = "shibai", commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy",value = "SEMAPHORE")
    })//"execution.isolation.strategy",默认不建议修改这个值,如果遇到问题再修改,否则不建议
    @GetMapping("/order/{id}")
    public User getOrder(@PathVariable Long id){
        //访问提供者，获取数据
        InstanceInfo instanceInfo = erekaClient.getNextServerFromEureka("provide-user", false);
        String homePageUrl = instanceInfo.getHomePageUrl();
        User user = restTemplate.getForObject(homePageUrl+"/user/"+id, User.class);
        //通过访问 Rest 获取 Json 数据，然后转换为 User 对象
        return user;
    }

    public User shibai(Long id){
        User user = new User();
        user.setId(-100);
        user.setDate(new Date());
        return user;
    }

}
