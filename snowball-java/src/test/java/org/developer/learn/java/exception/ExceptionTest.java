package org.developer.learn.java.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @ClassName ExceptionTest
 * @Description:
 * @Author lfq
 * @Date 2021/4/26
 **/
@Slf4j
public class ExceptionTest {
    @Test
    public void finallyTest() {
        divide(1, 0);
    }

    /**
     * finally一定会执行
     *
     * @param a
     * @param b
     * @return
     */
    public int divide(int a, int b) {
        int r = 0;
        try {
            r = a / b;
        } catch (Exception e) {
            return r;
        } finally {
            log.info("r:{}", r);
        }
        return r;
    }
}
