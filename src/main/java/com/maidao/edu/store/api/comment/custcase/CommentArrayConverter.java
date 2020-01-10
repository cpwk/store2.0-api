package com.maidao.edu.store.api.comment.custcase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.maidao.edu.store.api.comment.entity.CommentItems;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CommentArrayConverter implements AttributeConverter<CommentItems, String> {
    @Override
    public String convertToDatabaseColumn(CommentItems obj) {
        return JSON.toJSONString(obj);
    }

    @Override
    public CommentItems convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseObject(data, CommentItems.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
