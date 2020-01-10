package com.maidao.edu.store.api.address.repository;

import com.maidao.edu.store.api.address.model.Address;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

import java.util.List;

public interface IAddressRepository extends BaseRepository<Address, Integer> {
    List<Address> findByUserId(Integer userId);
}