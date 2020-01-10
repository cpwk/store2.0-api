package com.maidao.edu.store.api.vip.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.vip.model.Vip;
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
@RequestMapping("/adm/vip")
public class VipController extends BaseController {

    @Autowired
    private VipService vipService;

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView save(String vip) throws Exception {
        vipService.save(parseModel(vip, new Vip()));
        return feedback(null);
    }

    @RequestMapping(value = "/vips")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView vips(String vipQo) throws Exception {
        return feedback(vipService.vips(parseModel(vipQo, new VipQo())));
    }

    @RequestMapping(value = "/vip")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView vip(Integer id) throws Exception {
        return feedback(vipService.vip(id));
    }

    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    @RequestMapping(value = "/search")
    public ModelAndView search(Integer grade) throws Exception {
        return feedback(vipService.search(grade));
    }

    @RequestMapping(value = "/remove")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView remove(Integer id) throws Exception {
        vipService.remove(id);
        return feedback(null);
    }

    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    @RequestMapping(value = "/mod_status")
    public ModelAndView modStatus(Integer id, Integer status) throws Exception {
        vipService.modStatus(id, status);
        return feedback();
    }

    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    @RequestMapping(value = "/vipUsers")
    public ModelAndView vipUsers(String vipUserQo) throws Exception {
        return feedback(vipService.vipUsers(parseModel(vipUserQo, new VipUserQo())));
    }

    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    @RequestMapping(value = "/renew")
    public ModelAndView renew(Integer id, String duration, String remark) throws Exception {
        vipService.renew(id, duration, remark);
        return feedback();
    }

    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    @RequestMapping(value = "/admNewVipUser")
    public ModelAndView admNewVipUser(String vipUser, String phone) throws Exception {
        vipService.admNewVipUser(parseModel(vipUser, new VipUser()), phone);
        return feedback();
    }

    @RequestMapping(value = "/expire")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.VIP_EDIT)
    public ModelAndView expire(Integer id) throws Exception {
        vipService.expire(id);
        return feedback(null);
    }

}
