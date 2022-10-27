package com.cedaniel200.todolist.domain.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isNullOrBlank(String value){
        return value == null || value.isBlank();
    }

    public static boolean isNotNullOrBlank(String value){
        return !isNullOrBlank(value);
    }

    public static boolean isNotEmail(String value) {
        String reg = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(reg);
        Matcher mather = pattern.matcher(value);
        return !mather.find();
    }
}
