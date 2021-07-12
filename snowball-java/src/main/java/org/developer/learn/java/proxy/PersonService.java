package org.developer.learn.java.proxy;

import lombok.extern.slf4j.Slf4j;
import org.developer.learn.java.pojo.Person;
import org.developer.learn.java.util.GsonUtil;

/**
 * @ClassName PersonService
 * @Description: Cglib动态代理增强的目标类
 * @Author lfq
 * @Date 2020/5/13
 **/
@Slf4j
public class PersonService {
    public void save(Person person) {
        log.info("save person invoked,param:{}", GsonUtil.toJsonString(person));
    }
}
