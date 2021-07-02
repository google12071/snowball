package org.developer.learn.java.string;

/**
 * @ClassName EmojiUtil
 * @Description:
 * @Author lfq
 * @Date 2021/7/1
 **/
public class EmojiUtil {
    private static Object executeEncrypt(Object value) {
        if (value == null) {
            return null;
        }
        String val = String.valueOf(value);
        if (val.startsWith("云友")) {
            return buildYunYouEncrypt(val);
        } else {
            return buildCommonEncrypt(val);
        }
    }

    private static String buildEncrypt(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= num; i++) {
            sb.append("*");
        }
        return sb.toString();
    }

    /**
     * 常规用户名处理
     *
     * @param value
     * @return
     */
    private static String buildCommonEncrypt(String value) {
        int codePointCount = value.codePointCount(0, value.length());
        if (codePointCount == 1) {
            return value;
        } else if (codePointCount == 2) {
            int start = value.offsetByCodePoints(0, 0);
            int end = value.offsetByCodePoints(0, 1);
            return value.substring(start, end) + "*";
        } else {
            //第一个字符
            String first = value.substring(value.offsetByCodePoints(0, 0),
                    value.offsetByCodePoints(0, 1));
            //最后一个字符
            String last = value.substring(value.offsetByCodePoints(0, codePointCount - 1),
                    value.offsetByCodePoints(0, codePointCount));
            return first + buildEncrypt(codePointCount - 2) + last;
        }
    }

    /**
     * 云友用户名称处理
     *
     * @param value
     * @return
     */
    private static String buildYunYouEncrypt(String value) {
        int codePointCount = value.codePointCount(0, value.length());
        if (codePointCount <= 3) {
            return "云友" + "*";
        } else if (codePointCount == 4) {
            //最后一个字符
            String last = value.substring(value.offsetByCodePoints(0, codePointCount - 1),
                    value.offsetByCodePoints(0, codePointCount));
            return "云友" + "*" + last;
        } else if (codePointCount == 5) {
            //最后2个字符
            String last2 = value.substring(value.offsetByCodePoints(0, codePointCount - 2),
                    value.offsetByCodePoints(0, codePointCount));
            return "云友" + "*" + last2;
        } else if (codePointCount == 6) {
            //最后3个字符
            String last3 = value.substring(value.offsetByCodePoints(0, codePointCount - 3),
                    value.offsetByCodePoints(0, codePointCount));
            return "云友" + "*" + last3;

        } else {
            //最后4个字符
            String last4 = value.substring(value.offsetByCodePoints(0, codePointCount - 4),
                    value.offsetByCodePoints(0, codePointCount));
            return "云友" + buildEncrypt(codePointCount - 6) + last4;
        }
    }

}
