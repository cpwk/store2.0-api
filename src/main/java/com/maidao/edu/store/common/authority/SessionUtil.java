package com.maidao.edu.store.common.authority;

import com.maidao.edu.store.api.admin.authority.AdminSessionWrap;
import com.maidao.edu.store.api.admin.model.Admin;
import com.maidao.edu.store.api.admin.model.AdminSession;
import com.maidao.edu.store.api.admin.service.IAdminService;
import com.maidao.edu.store.api.user.model.User;
import com.maidao.edu.store.api.user.model.UserSession;
import com.maidao.edu.store.api.user.model.UserSessionWrap;
import com.maidao.edu.store.api.user.service.UserService;
import com.maidao.edu.store.common.context.SessionWrap;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static com.maidao.edu.store.common.exception.ErrorCode.*;

public class SessionUtil {

    public static Map<String, SessionWrap> map = new HashMap<>();
    @Autowired
    private IAdminService adminService;
    @Autowired
    private UserService userService;

    public static SessionWrap getSession(String token) {
        return map.get(token);
    }

    public static boolean tokenTimeout(String token) {
        if (map.get(token) == null) {
            return true;
        } else {
            SessionWrap wrap = map.get(token);
            if (wrap == null) {
                return true;
            }
            if (wrap instanceof AdminSessionWrap) {
                AdminSession session = ((AdminSessionWrap) wrap).getAdminSession();
                return session == null || session.getExpireAt() <= (new Date().getTime());
            } else {
                return true;
            }
        }

    }

    public static void putSession(String token, SessionWrap sess) {
        if (map == null || map.isEmpty()) {
            map = new HashMap<>();
        }
        map.put(token, sess);
    }

    public static void removeSession(String token) {
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (token.equals(key)) {
                iterator.remove();
                map.remove(key);
            }
        }
    }

    public SessionWrap adminPermissionCheck(Enum type, String token, String permission) throws ServiceException {

        if (tokenTimeout(token)) {
            if (type == AdminType.ADMIN) {
                AdminSession session = adminService.adminSession(token);

                if (session != null && session.getExpireAt() > (new Date().getTime())) {
                    Admin admin = adminService.admin(session.getAdminId(), true);
                    if (admin != null && admin.getStatus() == 1) {
                        AdminSessionWrap wrap = new AdminSessionWrap(admin, session);
                        putSession(token, wrap);
                        return wrap;
                    } else {
                        throw new ServiceException(ERR_ADMIN_NOTEXIST);
                    }
                } else {
                    throw new ServiceException(ERR_SESSION_EXPIRES);
                }
            } else if (type == AdminType.USER) {
                UserSession session = userService.findByToken(token);

                if (session != null && session.getExpireAt() > (new Date().getTime())) {
                    User user = userService.user(session.getUserId(), true);
                    if (user != null) {
                        UserSessionWrap wrap = new UserSessionWrap(user, session);
                        putSession(token, wrap);
                        return wrap;
                    } else {
                        throw new ServiceException(ERR_PERMISSION_DENIED);
                    }
                } else {
                    throw new ServiceException(ERR_SESSION_EXPIRES);
                }
            }
        }

        {
            boolean pass = false;

            SessionWrap wrap = getSession(token);
            if (wrap == null) {
                throw new ServiceException(ERR_SESSION_EXPIRES);
            }

            if (StringUtils.isEmpty(permission) || permission.equals("NONE")) {
                pass = true;
            } else {
                List<String> ps = new ArrayList<>();
                if (wrap instanceof AdminSessionWrap) {
                    Admin admin = ((AdminSessionWrap) wrap).getAdmin();
                    ps = admin.getRole().getPermissions();
                }
                pass = ps.contains(permission);
            }
            if (pass) {
                return wrap;
            } else {
                throw new ServiceException(ERR_PERMISSION_DENIED);
            }
        }
    }
}