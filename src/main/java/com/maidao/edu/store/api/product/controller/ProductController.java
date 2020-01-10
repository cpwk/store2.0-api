package com.maidao.edu.store.api.product.controller;

import com.alibaba.fastjson.JSON;
import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.product.model.Product;
import com.maidao.edu.store.api.product.qo.ProductQo;
import com.maidao.edu.store.api.product.service.IProductService;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adm/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView save(String product) throws Exception {
        productService.save(parseModel(product, new Product()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView remove(Integer id) throws Exception {
        productService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/product")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView product(Integer id) throws Exception {
        return feedback(productService.product(id));
    }

    @RequestMapping(value = "/products")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView products(String productQo) throws Exception {
        return feedback(productService.products(parseModel(productQo, new ProductQo()), true));
    }

    @RequestMapping(value = "/outsome")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView outSome(String ids) throws Exception {
        productService.outSome(JSON.parseArray(ids, Integer.class));
        return feedback();
    }

    @RequestMapping(value = "/putsome")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.PRODUCT_EDIT)
    public ModelAndView putSome(String ids) throws Exception {
        productService.putSome(JSON.parseArray(ids, Integer.class));
        return feedback();
    }

    @RequestMapping(value = "/findbyids")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView findByIds(String ids) throws Exception {
        return feedback(productService.findByIds(JSON.parseArray(ids, Integer.class)));
    }

    @RequestMapping(value = "/findbycodes")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView findBycodes(String codes) throws Exception {
        return feedback(productService.findByCodes(JSON.parseArray(codes, String.class)));

    }

    @RequestMapping(value = "/products_user")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView productsUser(String productQo) throws Exception {
        return feedback(productService.products(parseModel(productQo, new ProductQo()), true));
    }
}