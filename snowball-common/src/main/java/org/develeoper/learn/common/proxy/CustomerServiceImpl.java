package org.develeoper.learn.common.proxy;


import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName CustomerServiceImpl
 * @Description:
 * @Author lfq
 * @Date 2020/5/12
 **/
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void eating(String name) {
        System.out.println("my name is " + name + ",i am eating");
    }
}
