package com.maidao.edu.store.api.coupon.rulesconverter;

import com.alibaba.fastjson.JSON;
import com.maidao.edu.store.api.coupon.entity.CouponRule;
import com.sunnysuperman.commons.bean.Bean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RulesConverter implements AttributeConverter<CouponRule, String> {

    @Override
    public String convertToDatabaseColumn(CouponRule list) {
        return JSON.toJSONString(list);
    }

    @Override
    public CouponRule convertToEntityAttribute(String data) {
        try {
            return Bean.fromJson(data, new CouponRule());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
