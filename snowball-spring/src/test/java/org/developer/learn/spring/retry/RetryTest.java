package org.developer.learn.spring.retry;

import org.developer.learn.spring.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName RetryTest
 * @Description:
 * @Author lfq
 * @Date 2021/4/22
 **/
public class RetryTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void retry() throws Exception {
        userService.retry(1L);
    }
}
