package com.maidao.edu.store.api.vip.qo;

import com.maidao.edu.store.common.reposiotry.support.DataQueryObjectSort;
import com.maidao.edu.store.common.reposiotry.support.QueryField;
import com.maidao.edu.store.common.reposiotry.support.QueryType;

public class VipQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status;

    @QueryField(type = QueryType.DEFAULT_LIKE, name = "name")
    private String name;

    @QueryField(type = QueryType.EQUAL, name = "grade")
    private Integer grade;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
