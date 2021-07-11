package org.developer.learn.java.dp.creational.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * 工厂模式测试类
 * <p>
 * 工厂模式(1、简单工厂模式、2 工厂方法模式、3 抽象工厂模式)
 */
@Slf4j
public class FactoryTest {

    public static void main(String[] args) {
        LoggerFactory factory1=new DatabaseLoggerFactory();
        factory1.createLogger();

        LoggerFactory factory2=new FileLoggerFactory();
        factory2.createLogger();
    }
}
