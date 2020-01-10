package com.maidao.edu.store.api.user.model;


import com.maidao.edu.store.common.context.SessionWrap;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-09 13:58
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:UserSessionWrap
 * 类描述:将用户类和用户会话类糅合在一起
 **/

public class UserSessionWrap implements SessionWrap {

    private User user;
    private UserSession userSession;

    public UserSessionWrap(User user, UserSession userSession) {
        this.user = user;
        this.userSession = userSession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}