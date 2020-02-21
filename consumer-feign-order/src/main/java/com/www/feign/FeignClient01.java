package com.www.feign;

import com.www.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("provide-user")
public interface FeignClient01 {
    @GetMapping("/user/{id}")
    User getUser(@PathVariable("id") Long id);
}
