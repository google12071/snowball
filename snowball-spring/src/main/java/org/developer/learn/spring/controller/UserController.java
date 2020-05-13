package org.developer.learn.spring.controller;

import org.developer.learn.common.pojo.User;
import org.developer.learn.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description:
 * @Author lfq
 * @Date 2020/5/10
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public User getUserByUid(@PathVariable Long uid) {
        return userService.getUserById(uid);
    }
}
