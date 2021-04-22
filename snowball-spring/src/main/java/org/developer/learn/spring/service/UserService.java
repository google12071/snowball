package org.developer.learn.spring.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.developer.learn.common.pojo.User;
import org.developer.learn.spring.aspect.annotation.MethodRetry;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserService
 * @Description:
 * @Author lfq
 * @Date 2020/5/10
 **/
@Slf4j
@Service
public class UserService {

    private static final List<User> userList = Lists.newArrayList(
            new User(1L, "张三", 20, "安徽省宿州市", "高级开发工程师", 18000.00),
            new User(2L, "李四", 19, "浙江省杭州市", "产品经历", 20000.00),
            new User(3L, "王武", 21, "北京市", "测试开发工程师", 15000.00),
            new User(4L, "马六", 22, "河南省郑州市", "高级运营经理", 15000.00),
            new User(5L, "田七", 23, "福建省福州市", "前端开发工程师", 20000.00)
    );

    public User getUserById(Long uid) {
        return userList.stream().filter(x -> x.getUid().equals(uid)).findAny().orElse(null);
    }


    @MethodRetry(retryTimes = 1, backoff = 200, retryOnException = ArithmeticException.class)
    public void retry(Long uid) {
        log.info("retry....");
        int a = 1;
        int b = 0;
        System.out.println(a / b);
    }
}
