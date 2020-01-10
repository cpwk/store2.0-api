package com.maidao.edu.store.api.product.repository;

import com.maidao.edu.store.api.product.model.Product;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

import java.util.List;

public interface IProductRepository extends BaseRepository<Product, Integer> {

    List<Product> findAllByIdIn(List<Integer> ids);

    List<Product> findAllBySortIdIn(List<String> codes);

}