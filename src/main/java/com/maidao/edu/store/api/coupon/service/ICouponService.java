package com.maidao.edu.store.api.coupon.service;

import com.maidao.edu.store.api.coupon.model.Coupon;
import com.maidao.edu.store.api.coupon.qo.CouponQo;
import com.maidao.edu.store.common.exception.ServiceException;

import java.util.List;

public interface ICouponService {

    void save(Coupon coupon) throws ServiceException;

    void remove(Integer id) throws Exception;

    Coupon coupon(Integer id);

    List<Coupon> coupons(CouponQo qo);

}
