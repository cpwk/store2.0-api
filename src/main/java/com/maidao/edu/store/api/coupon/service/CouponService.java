package com.maidao.edu.store.api.coupon.service;

import com.maidao.edu.store.api.coupon.model.Coupon;
import com.maidao.edu.store.api.coupon.qo.CouponQo;
import com.maidao.edu.store.api.coupon.repository.CouponRepository;
import com.maidao.edu.store.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService implements ICouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public void save(Coupon coupon) throws ServiceException {
        couponRepository.save(coupon);
    }

    @Override
    public void remove(Integer id) throws Exception {
        couponRepository.deleteById(id);
    }

    @Override
    public Coupon coupon(Integer id) {
        return couponRepository.getOne(id);
    }

    @Override
    public List<Coupon> coupons(CouponQo qo) {
        return couponRepository.findAll(qo);
    }
}