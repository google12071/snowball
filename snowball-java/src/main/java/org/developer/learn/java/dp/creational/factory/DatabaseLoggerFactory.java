package org.developer.learn.java.dp.creational.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseLoggerFactory implements LoggerFactory {
    @Override
    public Logger createLogger() {
        log.info("DatabaseLogger connected");
        Logger logger = new DataBaseLogger();
        log.info("DatabaseLogger build success");
        return logger;
    }
}
