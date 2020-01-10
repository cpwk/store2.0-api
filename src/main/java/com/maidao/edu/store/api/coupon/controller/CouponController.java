package com.maidao.edu.store.api.coupon.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.coupon.model.Coupon;
import com.maidao.edu.store.api.coupon.qo.CouponQo;
import com.maidao.edu.store.api.coupon.service.CouponService;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adm/coupon")
public class CouponController extends BaseController {


    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COUPON_EDIT)
    public ModelAndView save(String coupon) throws Exception {
        couponService.save(parseModel(coupon, new Coupon()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COUPON_EDIT)
    public ModelAndView remove(Integer id) throws Exception {
        couponService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/coupon")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COUPON_EDIT)
    public ModelAndView coupon(Integer id) throws Exception {
        return feedback(couponService.coupon(id));
    }

    @RequestMapping(value = "/coupons")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.COUPON_EDIT)
    public ModelAndView coupons(String couponQo) throws Exception {
        return feedback(couponService.coupons(parseModel(couponQo, new CouponQo())));
    }

}
