package com.maidao.edu.store.api.vip.controller;

import com.maidao.edu.store.api.vip.model.VipUser;
import com.maidao.edu.store.api.vip.qo.VipQo;
import com.maidao.edu.store.api.vip.qo.VipUserQo;
import com.maidao.edu.store.api.vip.service.VipService;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usr/vip")
public class VipUserController extends BaseController {

    @Autowired
    private VipService vipService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView save(String vipUser) throws Exception {
        vipService.save(parseModel(vipUser, new VipUser()));
        return feedback(null);
    }

    @RequestMapping(value = "/vipuser")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView vipuser(Integer id) throws Exception {
        return feedback(vipService.vipUser(id));
    }

    @RequestMapping(value = "/vipusers")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView vipusers(String vipUserQo) throws Exception {
        return feedback(vipService.vipUsers(parseModel(vipUserQo, new VipUserQo())));
    }

    @RequiredPermission(adminType = AdminType.USER)
    @RequestMapping(value = "/userVip")
    public ModelAndView userVip() throws Exception {
        return feedback(vipService.userVip());
    }

    @RequiredPermission(adminType = AdminType.USER)
    @RequestMapping(value = "/profileVip")
    public ModelAndView profile() throws Exception {
        return feedback(vipService.profile());
    }

    @RequiredPermission(adminType = AdminType.USER)
    @RequestMapping(value = "/newVip")
    public ModelAndView newVip(Integer id, Integer sno) throws Exception {
        vipService.newVip(id, sno);
        return feedback();
    }

    @RequiredPermission(adminType = AdminType.USER)
    @RequestMapping(value = "/userRenew")
    public ModelAndView userRenew(Integer id, Integer sno) throws Exception {
        vipService.userRenew(id, sno);
        return feedback();
    }

    @RequestMapping(value = "/vips")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView vips(String vipQo) throws Exception {
        return feedback(vipService.vips(parseModel(vipQo, new VipQo())));
    }
}
