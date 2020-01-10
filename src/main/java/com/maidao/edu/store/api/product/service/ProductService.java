package com.maidao.edu.store.api.product.service;

import com.maidao.edu.store.api.product.model.Product;
import com.maidao.edu.store.api.product.qo.ProductQo;
import com.maidao.edu.store.api.product.repository.IProductRepository;
import com.maidao.edu.store.api.sort.service.ISortService;
import com.maidao.edu.store.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ISortService iSortService;

    @Override
    public Product product(int id) {
        return productRepository.getOne(id);
    }

    @Override
    public void save(Product product) throws ServiceException {
        productRepository.save(product);
    }

    @Override
    public void remove(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public void outSome(List<Integer> ids) {
        for (Integer id : ids) {
            Product exist = productRepository.getOne(id);
            exist.setStatus(2);
            productRepository.save(exist);
        }
    }

    @Override
    public void putSome(List<Integer> ids) {
        for (Integer id : ids) {
            Product exist = productRepository.getOne(id);
            exist.setStatus(1);
            productRepository.save(exist);
        }
    }

    @Override
    public Page<Product> products(ProductQo qo, boolean admin) {
        if (!admin) {
            qo.setStatus(1);
        }
        iSortService.codes2Ids(qo);
        return productRepository.findAll(qo);
    }

    @Override
    public Page<Product> homeProducts(ProductQo qo) {

        if (qo.getFirstSortId() != null) {
            iSortService.firstSortId2Ids(qo);
        }

        return productRepository.findAll(qo);
    }

    @Override
    public List<Product> findByIds(List<Integer> ids) {

        return productRepository.findAllByIdIn(ids);
    }

    @Override
    public List<Product> findByCodes(List<String> codes) {
        return productRepository.findAllBySortIdIn(codes);
    }
}