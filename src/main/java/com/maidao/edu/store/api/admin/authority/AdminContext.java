package com.maidao.edu.store.api.admin.authority;

import com.maidao.edu.store.common.context.Context;
import com.maidao.edu.store.common.context.Contexts;
import com.maidao.edu.store.common.context.SessionWrap;

public class AdminContext {

    public static AdminSessionWrap getSessionWrap() {
        Context context = Contexts.get();
        if (context == null) {
            return null;
        }
        SessionWrap session = context.getSession();
        if (session == null) {
            return null;
        }
        if (!(session instanceof AdminSessionWrap)) {
            return null;
        }
        return (AdminSessionWrap) session;
    }
}