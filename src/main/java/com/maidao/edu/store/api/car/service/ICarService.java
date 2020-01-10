package com.maidao.edu.store.api.car.service;


import com.maidao.edu.store.api.car.model.Car;
import com.maidao.edu.store.common.exception.ServiceException;

import java.util.List;

public interface ICarService {

    List<Car> cars() throws Exception;

    Car car(int id);

    Car save(Car car) throws ServiceException;

    void remove(int id);

    List<Car> findByIds(List<Integer> ids);
}
