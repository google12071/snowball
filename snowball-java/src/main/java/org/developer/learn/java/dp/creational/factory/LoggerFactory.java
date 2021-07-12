package org.developer.learn.java.dp.creational.factory;

/**
 * 工厂方法模式（缺点：引入大量的工厂实现类，类膨胀）
 *
 */
public interface LoggerFactory {
    /**
     * 创建日志采集器
     *
     * @return
     */
    Logger createLogger();
}
