package com.maidao.edu.store.api.coupon.model;

import javax.persistence.*;

@Entity
@Table(name = "coupon_user")
public class UserCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "coupon_id")
    private Integer couponId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "get_at")
    private Long getAt;

    @Column(name = "expir_at")
    private Long expirAt;

    @Column(name = "status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getGetAt() {
        return getAt;
    }

    public void setGetAt(Long getAt) {
        this.getAt = getAt;
    }

    public Long getExpirAt() {
        return expirAt;
    }

    public void setExpirAt(Long expirAt) {
        this.expirAt = expirAt;
    }
}
