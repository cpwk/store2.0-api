package com.maidao.edu.store.api.coupon.service;

import com.maidao.edu.store.api.coupon.model.UserCoupon;
import com.maidao.edu.store.api.coupon.qo.UserCouponQo;
import com.maidao.edu.store.common.exception.ServiceException;

import java.util.List;

public interface IUserCouponService {

    void save(UserCoupon userCoupon) throws ServiceException;

    void remove(Integer id) throws Exception;

    void used(Integer id) throws Exception;


    UserCoupon coupon(Integer id);

    List<UserCoupon> userCoupons(UserCouponQo qo);

    void checkCoupon() throws Exception;

}
