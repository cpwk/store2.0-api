package com.maidao.edu.store.api.orders.converter;

import com.alibaba.fastjson.JSON;
import com.maidao.edu.store.api.address.model.Address;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AddressOrdersArrayConverter implements AttributeConverter<Address, String> {

    @Override
    public String convertToDatabaseColumn(Address address) {
        return JSON.toJSONString(address);
    }

    @Override
    public Address convertToEntityAttribute(String data) {
        try {
            return JSON.parseObject(data, Address.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
