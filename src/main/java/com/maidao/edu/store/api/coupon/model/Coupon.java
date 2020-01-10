package com.maidao.edu.store.api.coupon.model;

import com.maidao.edu.store.api.coupon.entity.CouponRule;
import com.maidao.edu.store.api.coupon.rulesconverter.RulesConverter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "code")
    private String code;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "status")
    private Integer status;

    @Column(name = "img")
    private String img;

    @Convert(converter = RulesConverter.class)
    @Column(name = "rule")
    private CouponRule rule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public CouponRule getRule() {
        return rule;
    }

    public void setRule(CouponRule rule) {
        this.rule = rule;
    }
}
