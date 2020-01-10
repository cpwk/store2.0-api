package com.maidao.edu.store.api.car.repository;

import com.maidao.edu.store.api.car.model.Car;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

import java.util.List;

public interface ICarRepository extends BaseRepository<Car, Integer> {

    List<Car> findByUserId(Integer userId);

    List<Car> findAllByIdIn(List<Integer> ids);

    Car findByUserIdAndProductIdAndSno(Integer userId, Integer productId, Integer sno);

}