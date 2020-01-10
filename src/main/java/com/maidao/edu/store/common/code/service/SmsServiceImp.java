package com.maidao.edu.store.common.code.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.maidao.edu.store.common.code.model.CodeCache;
import com.maidao.edu.store.common.code.model.SmsConfig;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.util.L;
import com.maidao.edu.store.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.maidao.edu.store.common.exception.ErrorCode.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:SmsServiceImp
 * 类描述:短信验证码服务层接口的一个具体实现类
 **/

@Service
public class SmsServiceImp implements SmsService {

    private static final int VCODE_LENGTH = 6;

    @Autowired
    private SmsConfig smsConfig;

    @Override
    public void sendVcode(String key, String mobile) throws ServiceException {
        if (StringUtils.isEmpty(key)) {

        }
        if (!StringUtils.isChinaMobile(mobile)) {
            throw new ServiceException(ERR_USER_MOBILE_INVALID);
        }
        String code = StringUtils.randomNumericString(VCODE_LENGTH);
        if (sendSms(mobile, code)) {
            CodeCache.saveCode(key, code);
            CodeCache.saveMobile(key, mobile);
        }
    }

    private boolean sendSms(String mobile, String code) throws ServiceException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsConfig.getAccessKeyId(), smsConfig
                .getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(smsConfig.getDomain());
        request.setVersion(smsConfig.getVersion());
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", smsConfig.getSignName());
        request.putQueryParameter("TemplateCode", smsConfig.getTemplateCode());
        Map<String, String> param = new HashMap<>();
        param.put("code", code);

        request.putQueryParameter("TemplateParam", JSON.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject data = JSON.parseObject(response.getData());
            if ("OK".equals(data.getString("Message"))) {
                L.info("Send sms of vcode.mobile:" + mobile + ",vcode:");
                return true;
            } else {
                L.error(response.getData());
                return false;
            }
        } catch (ServerException e) {
            throw new ServiceException(ERROR_SEND);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new ServiceException(ERR_ALIYUN_EXCEPTION);
        }
    }
}