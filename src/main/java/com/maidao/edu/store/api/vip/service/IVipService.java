package com.maidao.edu.store.api.vip.service;

import com.maidao.edu.store.api.vip.model.Vip;
import com.maidao.edu.store.api.vip.model.VipUser;
import com.maidao.edu.store.api.vip.qo.VipQo;
import com.maidao.edu.store.api.vip.qo.VipUserQo;
import com.maidao.edu.store.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IVipService {

    void save(Vip vip) throws ServiceException;

    void remove(Integer id) throws Exception;

    Vip vip(Integer id);

    List<Vip> vips(VipQo qo);

    Vip search(Integer grade);

    void modStatus(Integer id, Integer status);

    Page<VipUser> vipUsers(VipUserQo qo);

    void renew(Integer id, String duration, String remark); //续费

    void admNewVipUser(VipUser vipUser, String phone) throws Exception;

    void save(VipUser vipUser) throws ServiceException;

    void expire(Integer id) throws Exception;

    VipUser vipUser(Integer id);

    VipUser userVip();

    VipUser profile();

    void newVip(Integer id, Integer sno); //用户开通

    void userRenew(Integer id, Integer sno); //续费

}
