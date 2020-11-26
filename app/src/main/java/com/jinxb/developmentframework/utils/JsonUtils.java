package com.jinxb.developmentframework.utils;

import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
    public boolean isJsonObj(String text) {
        try {
            JSONObject object = JSONObject.parseObject(text);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
