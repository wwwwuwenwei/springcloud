package com.springcloud.controller;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.springcloud.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderRibbonController {
    @Autowired
    RestTemplate restTemplate;//spring 提供的一个用于访问 Rest 接口的模板对象
//    String url = "http://localhost:7090/user/";

    @Value("${user.url}")
    private String url;

    @Autowired
    EurekaClient eurekaClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/order/{id}")
    public User getOrder(@PathVariable Long id){
        //访问提供者，获取数据
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("provide-user", false);
        String homePageUrl = instanceInfo.getHomePageUrl();
        User user = restTemplate.getForObject("https://provide-user/user/"+id, User.class);
        //通过访问 Rest 获取 Json 数据，然后转换为 User 对象
        return user;
    }

    @GetMapping("/test")
    public String getTest(){
        //查找对应服务实例
        ServiceInstance serviceInstance1 = loadBalancerClient.choose("provide-user");
        System.err.println("1111"+serviceInstance1.getServiceId()+serviceInstance1.getHost()+serviceInstance1.getPort());

        ServiceInstance serviceInstance2 = loadBalancerClient.choose("provide-user1");
        System.err.println("2222"+serviceInstance2.getServiceId()+serviceInstance2.getHost()+serviceInstance2.getPort());

        return "1";
    }

}
