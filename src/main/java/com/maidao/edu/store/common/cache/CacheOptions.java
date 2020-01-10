package com.maidao.edu.store.common.cache;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:CacheOptions
 * 类描述:缓存的实体类，定义缓存对象的属性
 **/

public class CacheOptions {

    private String key;
    private int version;
    private int expireIn;

    public CacheOptions(String key, int version, int expireIn) {
        super();
        this.key = key;
        this.version = version;
        this.expireIn = expireIn;
    }

    public String getKey() {
        return key;
    }

    public int getVersion() {
        return version;
    }

    public int getExpireIn() {
        return expireIn;
    }
}