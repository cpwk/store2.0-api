package com.maidao.edu.store.api.address.service;

import com.maidao.edu.store.api.address.model.Address;
import com.maidao.edu.store.api.address.qo.AddressQo;
import com.maidao.edu.store.api.address.repository.IAddressRepository;
import com.maidao.edu.store.common.context.Contexts;
import com.maidao.edu.store.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-31 20:59
 * Version 1.8.0_211
 * 项目名称：store-api
 * 类名称:addressService
 * 类描述:TODO
 **/
@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressRepository iAddressRepository;

    @Override
    public void save(Address address) throws ServiceException {
        Integer userId = Contexts.requestUserId();
        address.setUserId(userId);
        iAddressRepository.save(address);
    }

    @Override
    public void remove(int id) {
        iAddressRepository.deleteById(id);
    }

    @Override
    public List<Address> addresss(AddressQo qo, boolean admin) {

        qo.setUserId(Contexts.requestUserId());

        return iAddressRepository.findAll(qo);
    }

    @Override
    public Address address(int id) {
        return iAddressRepository.getOne(id);
    }

    @Override
    public void def(Integer id) throws Exception {

        List<Address> addresses = iAddressRepository.findByUserId(Contexts.requestUserId());
        for (Address a : addresses) {
            if (a != null) {
                a.setDef(2);
                iAddressRepository.save(a);
            }
        }
        Address exist = iAddressRepository.getOne(id);
        exist.setDef(1);
        iAddressRepository.save(exist);
    }
}
