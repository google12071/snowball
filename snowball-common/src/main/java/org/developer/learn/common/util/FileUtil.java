package org.developer.learn.common.util;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName FileUtil
 * @Description:
 * @Author lfq
 * @Date 2021/6/16
 **/
public class FileUtil {
    public static String readByLine(String path, String split) {
        StringBuilder sb = new StringBuilder();
        //BufferedReader是可以按行读取文件
        try {
            FileInputStream inputStream = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str + split);
            }
            //close
            inputStream.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = sb.toString();
        return s.substring(0, s.lastIndexOf(","));
    }

    public static void main(String[] args) {
        String s = readByLine("/Users/lfq/Desktop/userId.txt", ",");
        System.out.println(s);
    }
}
