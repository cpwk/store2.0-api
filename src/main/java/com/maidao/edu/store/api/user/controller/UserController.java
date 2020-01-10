package com.maidao.edu.store.api.user.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.api.user.model.User;
import com.maidao.edu.store.api.user.qo.UserQo;
import com.maidao.edu.store.api.user.qo.UserSessionQo;
import com.maidao.edu.store.api.user.service.UserService;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.code.model.VCode;
import com.maidao.edu.store.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:UserController
 * 类描述:用户接口
 **/

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequiredPermission(adminType = AdminType.NONE)
    @RequestMapping(value = "sign_up")
    public ModelAndView sign_up(String user, String key, String smsCode) throws Exception {
        User model = parseModel(user, new User());
        userService.signUp(model, key, smsCode);
        return feedback(null);
    }

    @RequiredPermission(adminType = AdminType.NONE)
    @RequestMapping(value = "sign_in")
    public ModelAndView sign_in(String unknown, String password, String vCode) throws Exception {
        return feedback(userService.signIn(unknown, password, parseModel(vCode, new VCode()), getRemoteAddress()));
    }

    @RequiredPermission(adminType = AdminType.NONE)
    @RequestMapping(value = "/forget_password")
    public ModelAndView forget_password(String mobile, String vCode) throws Exception {
        return feedback(userService.forgetPassword(mobile, parseModel(vCode, new VCode())));
    }

    @RequiredPermission(adminType = AdminType.NONE)
    @RequestMapping(value = "/reset_password")
    public ModelAndView reset_password(String mobile, String password, String key, String smsCode) throws Exception {
        userService.resetPassword(mobile, password, key, smsCode);
        return feedback(null);
    }

    @RequiredPermission(adminType = AdminType.USER)
    @RequestMapping(value = "/update_password")
    public ModelAndView update_password(String mobile, String password, String newpassword, String vCode) throws Exception {
        userService.updatePassword(mobile, password, newpassword, parseModel(vCode, new VCode()));
        return feedback("success");
    }

    @RequiredPermission(adminType = AdminType.ADMIN)
    @RequestMapping(value = "/find_alluser")
    public ModelAndView find_AllUser(String userQo) throws Exception {
        return feedback(userService.findAllUser(parseModel(userQo, new UserQo())));
    }

    @RequestMapping(value = "delete_byid")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView delete_byid(Integer id) throws Exception {
        System.out.println(id);
        userService.deleteById(id);
        return feedback();
    }

    @RequestMapping(value = "find_byid")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView find_byid(Integer id) throws Exception {
        return feedback(userService.findById(id));
    }

    @RequestMapping(value = "/profile")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView profile() throws Exception {
        return feedback(userService.profile());
    }

    @RequestMapping(value = "/user_session")
    @RequiredPermission(adminType = AdminType.ADMIN, adminPermission = AdminPermission.ADMIN_LIST)
    public ModelAndView adminSessions(String userSessionQo) throws Exception {
        return feedback(userService.userSession(parseModel(userSessionQo, new UserSessionQo())));
    }

    @RequestMapping(value = "/save")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView save(String user) throws Exception {
        userService.save(parseModel(user, new User()));
        return feedback(null);
    }

    @RequestMapping(value = "/modMobile")
    @RequiredPermission(adminType = AdminType.USER)
    public ModelAndView save(String mobile, VCode vCode) throws Exception {
        userService.modMibile(mobile, vCode);
        return feedback(null);
    }
}
