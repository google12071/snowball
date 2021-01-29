package org.developer.learn.java.concurrent.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.developer.learn.common.util.CSVUtil;

import java.io.FileReader;
import java.io.Reader;

/**
 * @ClassName CSVWorker
 * @Description:CSV文件多线程操作类
 * @Author lfq
 * @Date 2021/1/29
 **/
@Slf4j
public class CSVWorker implements Runnable {
    private int threadId;

    private static final String filePathSuffix = "/uids.csv";

    public static volatile int num = 0;

    /**
     * 线程ID
     * @param threadId
     */
    public CSVWorker(int threadId) {
        this.threadId = threadId;
    }

    @SneakyThrows
    @Override
    public void run() {
        log.info("working threadName:{}", Thread.currentThread().getName());
        String filePath = this.getClass().getResource(filePathSuffix).getPath();
        Reader in = new FileReader(filePath);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader("uid").withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            long lineNumber = record.getRecordNumber();
            if (lineNumber % 10 == threadId) {
                log.info("lineNumber:{},threadId:{},uid:{}", lineNumber, threadId, record.get("uid"));
                increase();
            }
        }
    }

    public synchronized void increase() {
        num++;
    }

    public static int getNum() {
        return num;
    }
}
