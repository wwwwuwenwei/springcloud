package com.www.feign;

import com.www.config.FeignClient02Config;
import com.www.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "xxxx", url = "http://localhost:10000/", configuration = FeignClient02Config.class)
public interface FeignClient02 {
   @RequestMapping("/eureka/apps/{servicename}")
    String getServiceName(@PathVariable("servicename") String servicename);
}
