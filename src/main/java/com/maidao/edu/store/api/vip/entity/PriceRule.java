package com.maidao.edu.store.api.vip.entity;

import java.math.BigDecimal;

public class PriceRule {

    private String duration;
    private BigDecimal price;
    private Integer sno;

    public PriceRule() {
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }
}
