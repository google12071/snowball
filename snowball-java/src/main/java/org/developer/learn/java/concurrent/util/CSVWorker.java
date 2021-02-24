package org.developer.learn.java.concurrent.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

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

    private static final String pathSuffix = "/uids.csv";

    /**
     * 线程ID
     *
     * @param threadId
     */
    public CSVWorker(int threadId) {
        this.threadId = threadId;
    }

    @SneakyThrows
    @Override
    public void run() {
        String filePath = this.getClass().getResource(pathSuffix).getPath();
        Reader in = new FileReader(filePath);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader("uid").withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            long lineNumber = record.getRecordNumber();
            if (lineNumber % 10 == threadId) {
                log.info("lineNumber:{},threadId:{},uid:{}", lineNumber, threadId, record.get("uid"));
            }
        }
    }

}
