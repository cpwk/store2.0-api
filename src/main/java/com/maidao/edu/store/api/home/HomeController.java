package com.maidao.edu.store.api.home;

import com.maidao.edu.store.api.banner.qo.BannerQo;
import com.maidao.edu.store.api.banner.service.IBannerService;
import com.maidao.edu.store.api.comment.model.Comment;
import com.maidao.edu.store.api.comment.service.CommentService;
import com.maidao.edu.store.api.coupon.qo.CouponQo;
import com.maidao.edu.store.api.coupon.service.ICouponService;
import com.maidao.edu.store.api.product.qo.ProductQo;
import com.maidao.edu.store.api.product.service.IProductService;
import com.maidao.edu.store.api.sort.service.ISortService;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usr/home")
public class HomeController extends BaseController {

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICouponService couponService;

    @Autowired
    private ISortService sortService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/banners")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView banners(String bannerQo) throws Exception {
        return feedback(bannerService.banners(parseModel(bannerQo, new BannerQo()), false));
    }

    @RequestMapping(value = "/products")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView products(String productQo) throws Exception {
        return feedback(productService.homeProducts(parseModel(productQo, new ProductQo())));
    }

    @RequestMapping(value = "/coupons")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView coupons(String couponQo) throws Exception {
        return feedback(couponService.coupons(parseModel(couponQo, new CouponQo())));
    }

    @RequestMapping(value = "/sorts")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView sorts() {
        return feedback(sortService.sorts(true));
    }

    @RequiredPermission(adminType = AdminType.USER)
    @RequestMapping(value = "/saveComment")
    public ModelAndView save(String comment) throws Exception {
        commentService.save(parseModel(comment, new Comment()));
        return feedback(null);
    }

    @RequestMapping(value = "/removeComment")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView remove(Integer id) throws Exception {
        commentService.remove(id);
        return feedback(null);
    }

}