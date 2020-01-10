package com.maidao.edu.store.api.admin.service;

import com.maidao.edu.store.api.admin.authority.AdminContext;
import com.maidao.edu.store.api.admin.authority.AdminPermissionVO;
import com.maidao.edu.store.api.admin.authority.AdminSessionWrap;
import com.maidao.edu.store.api.admin.model.Admin;
import com.maidao.edu.store.api.admin.model.AdminSession;
import com.maidao.edu.store.api.admin.model.Role;
import com.maidao.edu.store.api.admin.qo.AdminSessionQo;
import com.maidao.edu.store.api.admin.repository.IAdminRepository;
import com.maidao.edu.store.api.admin.repository.IAdminSessionRepository;
import com.maidao.edu.store.api.admin.repository.IRoleRepository;
import com.maidao.edu.store.common.context.Context;
import com.maidao.edu.store.common.context.Contexts;
import com.maidao.edu.store.common.entity.Constants;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.ipseeker.IPSeekerUtil;
import com.maidao.edu.store.common.util.CollectionUtil;
import com.maidao.edu.store.common.util.DateUtils;
import com.maidao.edu.store.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.maidao.edu.store.common.exception.ErrorCode.*;

@Service
public class AdminService implements IAdminService {

    @Value("${admin.tokenSalt}")
    private String salt;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private IAdminSessionRepository adminSessionRepository;

    @Override
    public void update_password(String password, String oldPassword) throws ServiceException {

        AdminSessionWrap wrap = AdminContext.getSessionWrap();
        AdminSession session = wrap.getAdminSession();
        Admin admin = adminRepository.getOne(session.getAdminId());

        if (!admin.getPassword().equals(StringUtils.getMD5(oldPassword, salt))) {
            throw new ServiceException(ERR_ADMIN_PASSWORD_ERROR);
        }
        admin.setPassword(StringUtils.getMD5(password, salt));

        adminRepository.save(admin);
    }

    @Override
    public Page<AdminSession> adminSessions(AdminSessionQo qo) throws Exception {
        return adminSessionRepository.findAll(qo);
    }

    @Override
    public void save_role(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void remove_role(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role role(int id) {
        return roleRepository.getOne(id);
    }

    @Override
    public List<Role> roles(boolean init) {
        List<Role> roles = roleRepository.findAll();
        if (init) {
            for (Role r : roles) {
                r.setPstr(AdminPermissionVO.initPermissionsByPs(r.getPermissions()));
            }
        }
        return roles;
    }

    @Override
    public AdminSession adminSession(String token) {
        return adminSessionRepository.findByToken(token);
    }

    @Override
    public Map signin(Admin a, String ip) throws ServiceException {

        Admin admin = getAdmin(a.getUsername());

        if (admin == null)
            throw new ServiceException(ERR_ADMIN_NOTEXIST);

        if (admin.getPassword().equals(StringUtils.getMD5(a.getPassword(), salt))) {
            if (admin.getStatus() == Constants.STATUS_OK) {
                String token = StringUtils.getToken(salt);
                AdminSession session = saveAdminSession(admin, token, ip);
                admin.setSigninAt(System.currentTimeMillis());
                adminRepository.save(admin);

                AdminSessionWrap wrap = new AdminSessionWrap(admin, session);

                Context context = Contexts.get();
                context.setSession(wrap);

                return CollectionUtil.arrayAsMap("admin", admin, "session", session);

            } else {
                throw new ServiceException(ERR_ADMIN_NOTEXIST);
            }
        } else {
            throw new ServiceException(ERR_ADMIN_PASSWORD_ERROR);
        }

    }

    private AdminSession saveAdminSession(Admin admin, String token, String ip) {
        AdminSession session = new AdminSession();
        session.setAdminId(admin.getId());
        session.setRole(admin.getRole().getName());
        session.setName(admin.getName());
        session.setToken(token);
        session.setSigninAt(new Date().getTime());
        session.setExpireAt(DateUtils.getDate_days(new Date(), Constants.SESSION_EXPIRE_DAYS).getTime());
        session.setIp(ip);
        session.setLocation(IPSeekerUtil.getFullLocation(ip));
        adminSessionRepository.save(session);
        return session;
    }

    @Override
    public void save_admin(Admin admin) throws ServiceException {

        Admin oa = getAdmin(admin.getUsername());

        if (admin.getId() != null && admin.getId() > 0) {
            if (oa == null)
                throw new ServiceException(ERR_ADMIN_NOTEXIST);

            oa.setName(admin.getName());
            oa.setRoleId(admin.getRoleId());
            if (!StringUtils.isNull(admin.getPassword()))
                oa.setPassword(StringUtils.getMD5(admin.getPassword(), salt));

            adminRepository.save(oa);
        } else {
            if (oa != null) {
                throw new ServiceException(ERR_ADMIN_EXISTED);
            }
            admin.setPassword(StringUtils.getMD5(admin.getPassword(), salt));
            admin.setStatus(Constants.STATUS_OK);
            adminRepository.save(admin);
        }
    }

    @Override
    public Admin admin(int id, boolean init) {
        Admin admin = adminRepository.getOne(id);
        if (init && admin != null)
            admin.setRole(roleRepository.getOne(admin.getRoleId()));
        return admin;
    }

    @Override
    public void admin_status(int id, int status) throws ServiceException {

        if (id == 1)
            throw new ServiceException(UNREACHED);

        Admin admin = adminRepository.getOne(id);
        admin.setStatus(status);
        adminRepository.save(admin);
    }

    @Override
    public void remove_admin(int id) throws ServiceException {

        if (id == 1) {
            throw new ServiceException(UNREACHED);
        }
        adminRepository.deleteById(id);
    }

    @Override
    public List<Admin> admins() {

        List<Admin> admins = adminRepository.findAll(new Sort(Sort.Direction.ASC, "roleId"));
        List<Role> roles = roles(false);

        for (Admin admin : admins) {
            for (Role role : roles) {
                if (admin.getRoleId().intValue() == role.getId().intValue())
                    admin.setRole(role);
            }
        }
        return admins;

    }

    private Admin getAdmin(String username) {
        try {
            username = StringUtils.escapeSql(username);
            Admin admin = adminRepository.findByUsername(username);
            if (admin != null)
                admin.setRole(roleRepository.getOne(admin.getRoleId()));
            return admin;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Map profile() throws Exception {
        Integer adminId = Contexts.requestAdminId();
        Admin admin = admin(adminId, true);
        return CollectionUtil.arrayAsMap("admin", admin);
    }

}
