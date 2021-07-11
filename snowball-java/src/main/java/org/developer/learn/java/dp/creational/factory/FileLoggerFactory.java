package org.developer.learn.java.dp.creational.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileLoggerFactory implements LoggerFactory {
    @Override
    public Logger createLogger() {
        log.info("read logger from file");
        Logger logger = new FileLogger();
        log.info("build FileLogger");
        return logger;
    }
}
