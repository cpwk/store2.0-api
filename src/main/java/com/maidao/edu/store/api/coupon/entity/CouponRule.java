package com.maidao.edu.store.api.coupon.entity;

import java.util.List;

public class CouponRule {

    private Byte type; //1:满X减X，2：每X减X，3：直减
    private List<Integer> values;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
