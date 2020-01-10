package com.maidao.edu.store.api.user.repository;


import com.maidao.edu.store.api.user.model.UserSession;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-09 10:34
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:UserSessionRepository
 * 类描述:用户会话持久层接口
 **/

public interface UserSessionRepository extends BaseRepository<UserSession, Integer> {
    UserSession findByToken(String token);
}
