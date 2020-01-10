package com.maidao.edu.store.api.user.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-06 16:32
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:UserConfig
 * 类描述:用户配置类
 **/

@Component
@ConfigurationProperties(prefix = "user", ignoreInvalidFields = false)//对应application.properties的user配置
public class UserConfig {

    private String salt;

    private Integer sessionDays;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getSessionDays() {
        return sessionDays;
    }

    public void setSessionDays(Integer sessionDays) {
        this.sessionDays = sessionDays;
    }
}
