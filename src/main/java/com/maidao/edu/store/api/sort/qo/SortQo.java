package com.maidao.edu.store.api.sort.qo;


import com.maidao.edu.store.common.reposiotry.support.DataQueryObjectSort;
import com.maidao.edu.store.common.reposiotry.support.QueryField;
import com.maidao.edu.store.common.reposiotry.support.QueryType;

public class SortQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Byte status = 1;

    @QueryField(type = QueryType.FULL_LIKE, name = "name")
    private String name;

    private String sortPropertyName = "priority";

    public SortQo() {
    }

    public SortQo(Byte status) {
        this.setStatus(status);
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status == 0 ? null : status;
    }

    @Override
    public String getSortPropertyName() {
        return sortPropertyName;
    }

    @Override
    public void setSortPropertyName(String sortPropertyName) {
        this.sortPropertyName = sortPropertyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
