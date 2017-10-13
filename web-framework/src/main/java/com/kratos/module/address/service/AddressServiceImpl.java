package com.kratos.module.address.service;

import com.kratos.common.AbstractCrudService;
import com.kratos.common.PageRepository;
import com.kratos.module.address.domain.Address;
import com.kratos.module.address.domain.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AddressServiceImpl extends AbstractCrudService<Address> implements AddressService {
    private final AddressRepository addressRepository;
    @Override
    protected PageRepository<Address> getRepository() {
        return addressRepository;
    }

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}
