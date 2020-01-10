package com.maidao.edu.store.common.code.service;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:SmsService
 * 类描述:短信验证码服务层接口
 **/

public interface SmsService {
    void sendVcode(String key, String mobile) throws Exception;
}
