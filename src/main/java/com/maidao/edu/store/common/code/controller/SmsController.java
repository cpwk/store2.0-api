package com.maidao.edu.store.common.code.controller;

import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.code.service.SmsService;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:SmsController
 * 类描述:短信验证码接口
 **/

@Controller
@RequestMapping("/support/sms")
public class SmsController extends BaseController {
    @Autowired
    private SmsService smsService;

    @RequiredPermission(adminType = AdminType.NONE)
    @RequestMapping("/phone_vcode")
    public ModelAndView phone_vcode(String key, String mobile) throws Exception {
        smsService.sendVcode(key, mobile);
        return feedback("success");
    }
}
