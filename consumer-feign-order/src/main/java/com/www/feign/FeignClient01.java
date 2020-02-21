package com.www.feign;

import com.www.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("provide-user")
public interface FeignClient01 {
    @GetMapping("/user/{id}") // C 版本的 spring 是不能用 GetMapping 的,必须用 RequestMapping
    User getUser(@PathVariable("id") Long id);

    @GetMapping("/get-user")//无法访问，提供者这边必须是 POST 方式这边才可以用, 如果要用 GET 传递数据,只能以不同方式传递,不能封装为复杂对象
    User getPamUser(User user);//如果你参数传递的是复杂参数，那么不管你 feign 配置什么请求方式，他都会以 POST 的请求方式发出去

}
