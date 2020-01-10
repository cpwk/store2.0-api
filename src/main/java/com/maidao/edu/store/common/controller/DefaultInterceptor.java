package com.maidao.edu.store.common.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.authority.SessionUtil;
import com.maidao.edu.store.common.context.Context;
import com.maidao.edu.store.common.context.Contexts;
import com.maidao.edu.store.common.context.SessionThreadLocal;
import com.maidao.edu.store.common.context.SessionWrap;
import com.maidao.edu.store.common.entity.ApiParams;
import com.maidao.edu.store.common.exception.ErrorCode;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.util.WebUtils;
import com.sunnysuperman.commons.util.FormatUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class DefaultInterceptor extends SessionUtil implements HandlerInterceptor, ErrorCode {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (CrossDomainHandler.handle(request, response)) {
            return false;
        }

        Context wrapper = new Context();
        wrapper.setRequestIp(WebUtils.getRemoteAddress(request));
        wrapper.setCustomerId(FormatUtil.parseLong(WebUtils.getHeader(request, ApiParams.CUSTOMER_ID)));
        SessionThreadLocal.getInstance().set(wrapper);

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        boolean authorized = false;
        RequiredPermission requiredPermission = handlerMethod.getMethodAnnotation(RequiredPermission.class);
        for (AdminType adminType : requiredPermission.adminType()) {
            if (adminType == AdminType.ADMIN) {
                //多个权限满足其一即可
                for (AdminPermission permission : requiredPermission.adminPermission()) {
                    authorized = findSessionWrap(AdminType.ADMIN, request, permission.name()) != null;
                    if (authorized) {
                        break;
                    }
                }
            } else if (adminType == AdminType.USER) {
                authorized = findSessionWrap(AdminType.USER, request, null) != null;
            } else {
                // no session
                authorized = true;
            }
            if (authorized) {
                break;
            }
        }
        if (!authorized) {
            throw new ServiceException(ERR_SESSION_EXPIRES);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    private SessionWrap findSessionWrap(Enum type, HttpServletRequest request, String permission) throws Exception {
        String token = WebUtils.getHeader(request, ApiParams.ADMIN_TOKEN);
        SessionWrap wrap = adminPermissionCheck(type, token, permission);
        Contexts.get().setSession(wrap);
        return wrap;
    }

}