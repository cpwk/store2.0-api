package com.maidao.edu.store.api.vip.converter;

import com.alibaba.fastjson.JSON;
import com.maidao.edu.store.api.vip.entity.PriceRule;

import javax.persistence.AttributeConverter;
import java.util.List;

public class PriceRuleConverter implements AttributeConverter<List<PriceRule>, String> {

    @Override
    public String convertToDatabaseColumn(List<PriceRule> priceRule) {
        return JSON.toJSONString(priceRule);
    }

    @Override
    public List<PriceRule> convertToEntityAttribute(String data) {
        try {
            return JSON.parseArray(data, PriceRule.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
