package com.maidao.edu.store.api.address.controller;

import com.maidao.edu.store.api.address.model.Address;
import com.maidao.edu.store.api.address.qo.AddressQo;
import com.maidao.edu.store.api.address.service.AddressService;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/address")
public class AddressController extends BaseController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView save(String address) throws Exception {
        addressService.save(parseModel(address, new Address()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView remove(Integer id) throws Exception {
        addressService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/address")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView address(Integer id) throws Exception {
        return feedback(addressService.address(id));
    }

    @RequestMapping(value = "/addresss")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView addresss(String addressQo) throws Exception {
        return feedback(addressService.addresss(parseModel(addressQo, new AddressQo()), true));
    }

    @RequestMapping(value = "/def")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView def(Integer id) throws Exception {
        addressService.def(id);
        return feedback(null);
    }

}