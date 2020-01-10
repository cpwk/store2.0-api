package com.maidao.edu.store.api.product.productItemarrayconverter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.maidao.edu.store.api.product.entity.ProductItem;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter(autoApply = true)
public class ProductItemArrayConverter implements AttributeConverter<List<ProductItem>, String> {

    @Override
    public String convertToDatabaseColumn(List<ProductItem> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<ProductItem> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, ProductItem.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
