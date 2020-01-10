package com.maidao.edu.store.api.product.service;

import com.maidao.edu.store.api.product.model.Product;
import com.maidao.edu.store.api.product.qo.ProductQo;
import com.maidao.edu.store.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {

    Page<Product> products(ProductQo qo, boolean admin);

    Page<Product> homeProducts(ProductQo qo);

    Product product(int id);

    void save(Product product) throws ServiceException;

    void remove(int id);

    void outSome(List<Integer> ids);

    void putSome(List<Integer> ids);

    List<Product> findByIds(List<Integer> ids);

    List<Product> findByCodes(List<String> cods);
}
