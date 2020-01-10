package com.maidao.edu.store.api.admin.repository;


import com.maidao.edu.store.api.admin.model.AdminSession;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

public interface IAdminSessionRepository extends BaseRepository<AdminSession, Integer> {

    AdminSession findByToken(String token);

}