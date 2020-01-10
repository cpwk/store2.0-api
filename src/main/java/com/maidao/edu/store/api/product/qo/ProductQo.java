package com.maidao.edu.store.api.product.qo;

import com.maidao.edu.store.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.store.common.reposiotry.support.QueryField;
import com.maidao.edu.store.common.reposiotry.support.QueryType;

import java.util.List;

public class ProductQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.FULL_LIKE, name = "title")
    private String title;

    private Integer firstSortId;

    @QueryField(type = QueryType.IN, name = "sortId")
    private List<Integer> sortIds;

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status;

    private List<String> codes;

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Integer> getSortIds() {
        return sortIds;
    }

    public void setSortIds(List<Integer> sortIds) {
        this.sortIds = sortIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFirstSortId() {
        return firstSortId;
    }

    public void setFirstSortId(Integer firstSortId) {
        this.firstSortId = firstSortId;
    }
}