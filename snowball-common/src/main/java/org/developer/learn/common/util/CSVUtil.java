package org.developer.learn.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * @ClassName CSVUtil
 * @Description:
 * @Author lfq
 * @Date 2021/1/29
 **/
@Slf4j
public class CSVUtil {

    /**
     * 读取CSV文件，跳过表头
     *
     * @param fileName
     * @param headers
     * @throws IOException
     */
    public static void readCSV(String fileName, String[] headers) throws IOException {
        Reader in = new FileReader(fileName);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(headers).withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            log.info("userId:{},userName:{}", record.get("userId"), record.get("userName"));
        }

    }

    /**
     * 写入CSV文件
     *
     * @param fileName
     * @param headers
     * @param data
     *
     */
    public static void writeCSV(String fileName, String[] headers, Map<String, String> data) throws IOException {
        FileWriter out = new FileWriter(fileName);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(headers))) {
            data.forEach((author, title) -> {
                try {
                    printer.printRecord(author, title);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
