package com.blend.technology.utils;

import java.text.DecimalFormat;

public class StringUtils {
    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)) return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static Integer stringToInteger(String str) {
        Integer integer = null;
        try {
            integer = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return integer;
    }

    public static int stringToInt(String str) {
        int i = 0;
        try {
            i = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static String subString(String str, int startindex, int endindex) {
        String result = null;
        try {
            result = str.substring(startindex, endindex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getDisplayCount(String strCount) {
        int count = StringUtils.stringToInt(strCount);
        if (count > 99) {
            return "99+";
        }
        return strCount;
    }

//    public static boolean isList(List list){
//
//    }

    public static String DoubleToString(Double dou) {
        if (dou != null) {
            DecimalFormat df = new DecimalFormat("#.####");
            String format = df.format(dou);
            return format;
        } else {
            return "0";
        }
    }


    public static String DoubleToString2(Double dou) {
        if (dou != null) {
            DecimalFormat df = new DecimalFormat("#.##");
            String format = df.format(dou);
            return format;
        } else {
            return "0";
        }
    }
}
