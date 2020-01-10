package com.maidao.edu.store.api.orders.model;

import com.maidao.edu.store.api.address.model.Address;
import com.maidao.edu.store.api.car.model.Car;
import com.maidao.edu.store.api.orders.converter.AddressOrdersArrayConverter;
import com.maidao.edu.store.api.orders.converter.ProductOrdersArrayConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_num")
    private Long orderNum;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "total")
    private Long total;

    @Column(name = "created_at")
    private Long createdAt;

    @Convert(converter = AddressOrdersArrayConverter.class)
    @Column(name = "address")
    private Address address;

    @Convert(converter = ProductOrdersArrayConverter.class)
    @Column(name = "products")
    private List<Car> products;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Car> getProducts() {
        return products;
    }

    public void setProducts(List<Car> products) {
        this.products = products;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}