package com.maidao.edu.store.api.orders.service;

import com.maidao.edu.store.api.orders.model.Orders;
import com.maidao.edu.store.api.orders.qo.OrdersQo;
import com.maidao.edu.store.api.orders.repository.OrdersRepository;
import com.maidao.edu.store.common.context.Contexts;
import com.maidao.edu.store.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-31 20:59
 * Version 1.8.0_211
 * 项目名称：store-api
 * 类名称:ordersService
 * 类描述:TODO
 **/

@Service
public class OrdersService implements IOrdersService {

    @Autowired
    private OrdersRepository iOrderRepository;

    @Override
    public void save(Orders order) throws ServiceException {

        order.setOrderNum(System.currentTimeMillis());

        Integer userId = Contexts.requestUserId();
        order.setUserId(userId);

        order.setStatus(1);

        order.setCreatedAt(System.currentTimeMillis());

        iOrderRepository.save(order);
    }

    @Override
    public void pay(Orders order) throws ServiceException {

        Orders exist = iOrderRepository.getOne(order.getId());

        exist.setStatus(2);

        iOrderRepository.save(exist);
    }

    @Override
    public void receive(Orders order) throws ServiceException {

        Orders exist = iOrderRepository.getOne(order.getId());

        exist.setStatus(3);

        iOrderRepository.save(exist);
    }

    @Override
    public void evalProduct(Orders order) throws ServiceException {

        Orders exist = iOrderRepository.getOne(order.getId());

        exist.setStatus(4);

        iOrderRepository.save(exist);
    }

    @Override
    public void remove(int id) {
        iOrderRepository.deleteById(id);
    }

    @Override
    public Page<Orders> orders(OrdersQo qo, boolean admin) {
        return iOrderRepository.findAll(qo);
    }

    @Override
    public Orders order(int id) {
        return iOrderRepository.getOne(id);
    }
}
