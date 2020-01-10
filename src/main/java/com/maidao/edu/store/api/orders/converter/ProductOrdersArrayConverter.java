package com.maidao.edu.store.api.orders.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.maidao.edu.store.api.car.model.Car;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter(autoApply = true)
public class ProductOrdersArrayConverter implements AttributeConverter<List<Car>, String> {

    @Override
    public String convertToDatabaseColumn(List<Car> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<Car> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, Car.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
