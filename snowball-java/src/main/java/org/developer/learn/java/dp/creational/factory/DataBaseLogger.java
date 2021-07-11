package org.developer.learn.java.dp.creational.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataBaseLogger implements Logger {
    @Override
    public void writeLog() {
        log.info("database logger running...");
    }
}
