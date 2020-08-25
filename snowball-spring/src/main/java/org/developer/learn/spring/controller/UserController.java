package org.developer.learn.spring.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.developer.learn.common.pojo.User;
import org.developer.learn.spring.constant.ResourceNameDef;
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
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    @SentinelResource(value = ResourceNameDef.GET_USER_BY_UID, blockHandler = "exceptionHandler", fallback = "getUserByUidCallBack")
    public User getUserByUid(@PathVariable Long uid) {
        return userService.getUserById(uid);
    }

    public User getUserByUidCallBack(Long uid, Throwable t) {
        log.info("请求限制了:e={}, msg={},uid:{}", t.getClass().getName(), t.getMessage(), uid);
        return null;
    }
}
