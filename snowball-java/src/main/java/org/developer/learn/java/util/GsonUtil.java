package org.developer.learn.java.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * @ClassName GsonUtil
 * @Description:JSON格式化处理工具类
 * @Author lfq
 * @Date 2020/5/13
 **/
public class GsonUtil {
    private static final Gson gson = new Gson();

    /**
     * 构造器私有化
     */
    private GsonUtil() {
    }

    /**
     * 对象转换为JSON字符串
     *
     * @param object 对象
     * @return JSON字符串
     */
    public static String toJsonString(Object object) {
        return gson.toJson(object);
    }

    /**
     * json字符串转换成对象
     *
     * @param jsonString       json字符串
     * @param clazz<T>Class类对象
     * @return 对象
     */
    public static <T> T parseJSON(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, clazz);
    }

    /**
     * json字符串转成List
     *
     * @param jsonString
     * @param clazz<T>   Class类对象
     * @return
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, new TypeToken<List<T>>() {
        }.getType());
    }


    /**
     * List中元素为Map
     *
     * @param jsonString JSON字符串
     * @param <T>        对象类型
     * @return
     */
    public static <T> List<Map<String, T>> jsonToListMap(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString,
                new TypeToken<List<Map<String, T>>>() {
                }.getType());
    }

    /**
     * JSON字符串转map
     *
     * @param jsonString JSON字符串
     * @param <T>        对象类型
     * @return
     */
    public static <T> Map<String, T> jsonToMap(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, new TypeToken<Map<String, T>>() {
        }.getType());
    }
}
