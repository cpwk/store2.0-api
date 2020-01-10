package com.maidao.edu.store.api.sort.service;

import com.maidao.edu.store.api.product.qo.ProductQo;
import com.maidao.edu.store.api.sort.model.Sort;
import com.maidao.edu.store.common.exception.ServiceException;

import java.util.List;

public interface ISortService {

    List<Sort> sorts(boolean adm);

    Sort sort(int id) throws Exception;

    void save(Sort sort) throws ServiceException;

    void status(int id, byte status) throws ServiceException;

    void remove(int id) throws ServiceException;

    void codes2Ids(ProductQo qo);

    void firstSortId2Ids(ProductQo qo);

}