package com.cg.trg.boot.salon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.trg.boot.salon.bean.Address;

import com.cg.trg.boot.salon.dao.IAddressRepository;

@Service
public class AddressServiceImpl implements IAddressService {
	@Autowired
	IAddressRepository repository;
	@Override
	public Address addAddress(Address address) {
		// TODO Auto-generated method stub
		repository.save(address);
		return address;
		
	}

	@Override
	public Address removeAddress(long id) {
		// TODO Auto-generated method stub
		Optional<Address> addressToBeDeleted = repository.findById(id);
		repository.deleteById(id);
		
		if(addressToBeDeleted.isPresent()) {
			return addressToBeDeleted.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Address  updateAddress(long id, Address address) {
		// TODO Auto-generated method stub
		if(repository.existsById(id)) {
			Address addressToBeUpdated = repository.findById(id).get();
			repository.save(address);
			return addressToBeUpdated;
		}
		return null;
	}

	@Override
	public Address  getAddressDetails(long id) {
		// TODO Auto-generated method stub
		Optional<Address> address= repository.findById(id);
		if(address.isPresent()) {
			return address.get();
		}
		else {
		return null;
		}
	}

	@Override
	public List<Address> getAllAddress() {
		// TODO Auto-generated method stub
		List<Address> address = repository.findAll();
		return address;
		
	}

	

}