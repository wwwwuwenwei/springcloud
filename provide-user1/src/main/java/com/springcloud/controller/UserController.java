package com.springcloud.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.springcloud.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/info")
    public String getInfo(){
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("provide-user", false);
        return instanceInfo.getHomePageUrl();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
        return new User(id);
    }
}
