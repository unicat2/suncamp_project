package com.example.mybatisplus.common.utls;

import org.apache.commons.lang3.StringUtils;

public class MyStringUtils {
    public static boolean isEmptyOrIsNull(String s){
        return StringUtils.isBlank(s);
    }
}
