package com.maidao.edu.store.common.context;

import com.maidao.edu.store.api.admin.authority.AdminSessionWrap;
import com.maidao.edu.store.api.user.model.UserSessionWrap;
import com.maidao.edu.store.common.exception.ServiceException;

import static com.maidao.edu.store.common.exception.ErrorCode.*;

public class Contexts {

    public static void set(Context context) {
        SessionThreadLocal.getInstance().set(context);
    }

    public static Context get() {
        return SessionThreadLocal.getInstance().get();
    }

    public static SessionWrap getSession() {
        return get().getSession();
    }

    public static Integer requestAdminId() throws ServiceException {
        Context context = get();
        if (context == null) {
            throw new ServiceException(ERR_SESSION_EXPIRES);
        }
        Integer adminId = sessionAdminId();
        if (adminId == null) {
            throw new ServiceException(ERR_NEED_ADMIN_ID);
        }
        return adminId;
    }

    public static Integer sessionAdminId() throws ServiceException {
        Context context = get();
        if (context == null) {
            return null;
        }
        SessionWrap wrap = context.getSession();
        Integer adminId = null;

        if (wrap instanceof AdminSessionWrap) {
            adminId = ((AdminSessionWrap) wrap).getAdmin().getId();
        }
        return adminId;
    }

    public static Integer sessionUserId() throws ServiceException {
        Context context = get();
        if (context == null) {
            return null;
        }
        SessionWrap wrap = context.getSession();
        Integer id = null;
        if (wrap instanceof UserSessionWrap) {
            id = ((UserSessionWrap) wrap).getUser().getId();
        }
        return id;
    }

    public static Integer requestUserId() throws ServiceException {
        Context context = get();
        if (context == null) {
            throw new ServiceException(ERR_SESSION_EXPIRES);
        }
        Integer id = sessionUserId();
        if (id == null) {
            throw new ServiceException(ERR_NEED_USER_ID);
        }
        return id;
    }
}