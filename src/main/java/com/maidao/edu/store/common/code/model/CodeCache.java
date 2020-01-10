package com.maidao.edu.store.common.code.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:CodeCache
 * 类描述:手机验证码缓存实体类
 **/

@Component
public class CodeCache {

    private static Map<String, String> codeMap = new HashMap<>();

    private static Map<String, String> mobileMap = new HashMap<>();

    public static void saveCode(String key, String code) {
        codeMap.put(key, code);
    }

    public static String getCode(String key) {
        return codeMap.get(key);
    }

    public static void saveMobile(String key, String mobile) {
        mobileMap.put(key, mobile);
    }

    public static String getMobile(String key) {
        return mobileMap.get(key);
    }
}
