package org.developer.learn.java.dp.creational.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件日志生成器
 */
@Slf4j
public class FileLogger implements Logger {
    @Override
    public void writeLog() {
        log.info("fileLogger running...");
    }
}
