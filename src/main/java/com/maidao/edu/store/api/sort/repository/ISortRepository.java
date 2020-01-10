package com.maidao.edu.store.api.sort.repository;

import com.maidao.edu.store.api.sort.model.Sort;
import com.maidao.edu.store.common.reposiotry.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ISortRepository extends BaseRepository<Sort, Integer> {

    @Transactional
    @Modifying
    @Query("update Sort set status= :status where sequence like CONCAT(:sequence,'%')")
    void status(@Param(value = "status") Byte status, @Param(value = "sequence") String sequence);
}