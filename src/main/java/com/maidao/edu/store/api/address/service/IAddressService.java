package com.maidao.edu.store.api.address.service;


import com.maidao.edu.store.api.address.model.Address;
import com.maidao.edu.store.api.address.qo.AddressQo;
import com.maidao.edu.store.common.exception.ServiceException;

import java.util.List;

public interface IAddressService {

    List<Address> addresss(AddressQo qo, boolean admin);

    Address address(int id);

    void save(Address address) throws ServiceException;

    void remove(int id);

    void def(Integer id) throws Exception;
}
