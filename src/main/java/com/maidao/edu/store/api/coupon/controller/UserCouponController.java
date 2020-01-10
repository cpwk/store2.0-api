package com.maidao.edu.store.api.coupon.controller;

import com.maidao.edu.store.api.coupon.model.UserCoupon;
import com.maidao.edu.store.api.coupon.qo.UserCouponQo;
import com.maidao.edu.store.api.coupon.service.UserCouponService;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usr/coupon")
public class UserCouponController extends BaseController {

    @Autowired
    private UserCouponService couponService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView save(String userCoupon) throws Exception {
        couponService.save(parseModel(userCoupon, new UserCoupon()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView remove(Integer id) throws Exception {
        couponService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/used")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView used(Integer id) throws Exception {
        couponService.used(id);
        return feedback(null);
    }

    @RequestMapping(value = "/coupon")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView coupon(Integer id) throws Exception {
        return feedback(couponService.coupon(id));
    }

    @RequestMapping(value = "/usercoupons")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView usercoupons(String userCouponQo) throws Exception {
        return feedback(couponService.userCoupons(parseModel(userCouponQo, new UserCouponQo())));
    }

}
