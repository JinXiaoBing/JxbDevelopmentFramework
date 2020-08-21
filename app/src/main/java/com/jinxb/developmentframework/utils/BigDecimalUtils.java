package com.jinxb.developmentframework.utils;

import android.text.TextUtils;

import java.math.BigDecimal;

public class BigDecimalUtils {
    public static String add(String... num) {
        if (num == null) {
            return "0";
        }
        BigDecimal result = new BigDecimal("0");
        for (int i = 0; i < num.length; i++) {
            if (TextUtils.isEmpty(num[i])) {
                return "0";
            }
            try {
                result = result.add(new BigDecimal(num[i]));
            } catch (Exception e) {
                return "0";
            }
        }
        return result.toString();
    }
}
