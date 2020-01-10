package com.maidao.edu.store.api.vip.repository;

import com.maidao.edu.store.api.vip.model.Vip;
import com.maidao.edu.store.common.reposiotry.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface VipRepository extends BaseRepository<Vip, Integer> {

    Vip findByGrade(Integer grade);

    Vip findByName(String name);

    @Transactional
    @Modifying
    @Query("update Vip set status=:status where id = :id")
    void modStatusById(Integer id, Integer status);
}