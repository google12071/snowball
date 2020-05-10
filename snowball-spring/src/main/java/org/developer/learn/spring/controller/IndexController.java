package org.developer.learn.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Description:
 * @Author lfq
 * @Date 2020/5/10
 **/
@RestController
@RequestMapping("/index")
public class IndexController {
    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello,SpringBoot!";
    }
}
