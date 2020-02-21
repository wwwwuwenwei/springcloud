package com.www.controller;

import com.www.feign.FeignClient01;
import com.www.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    FeignClient01 feignClient01;

    @GetMapping("/order/{id}")
    public User getUser(@PathVariable Long id){
        return feignClient01.getUser(id);
    }

}
