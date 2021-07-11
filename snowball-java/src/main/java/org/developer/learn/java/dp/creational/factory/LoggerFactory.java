package org.developer.learn.java.dp.creational.factory;

/**
 * 抽象工厂
 */
public interface LoggerFactory {
    /**
     * 创建日志采集器
     *
     * @return
     */
    Logger createLogger();
}
