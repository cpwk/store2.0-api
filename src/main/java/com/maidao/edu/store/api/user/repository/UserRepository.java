package com.maidao.edu.store.api.user.repository;


import com.maidao.edu.store.api.user.model.User;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:08
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:UserRepository
 * 类描述:实现与数据库的交互，持久层是介于业务逻辑层和数据库之间的，访问数据库并为业务处理提供或保存数据
 **/

public interface UserRepository extends BaseRepository<User, Integer> {

    User findByMobile(String mobile) throws Exception;

    User findByEmail(String emial) throws Exception;

    User findByNick(String nick) throws Exception;

}
