package com.maidao.edu.store.api.vip.repository;

import com.maidao.edu.store.api.vip.model.VipUser;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

public interface VipUserRepository extends BaseRepository<VipUser, Integer> {

    VipUser findByUserId(Integer userId);

}