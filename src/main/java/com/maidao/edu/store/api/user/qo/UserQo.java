package com.maidao.edu.store.api.user.qo;

import com.maidao.edu.store.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.store.common.reposiotry.support.QueryField;
import com.maidao.edu.store.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-17 14:16
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:UserQo
 * 类描述:通过用户昵称进行查询
 **/

public class UserQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.FULL_LIKE, name = "nick")
    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
