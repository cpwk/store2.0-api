package com.maidao.edu.store.common.code.model;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:VCode
 * 类描述:图片验证码缓存实体类
 **/

public class VCode {

    private Long key;
    private String code;

    public VCode() {
    }

    public VCode(Long key, String code) {
        this.key = key;
        this.code = code;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
