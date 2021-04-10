package com.cg.trg.boot.salon.service;

import java.util.List;

import com.cg.trg.boot.salon.bean.Address;


public interface IAddressService {
	
	public Address addAddress(Address address);
	public Address removeAddress(long id);
	public Address  updateAddress(long id, Address address);
	public Address  getAddressDetails(long id);
	public List<Address> getAllAddress(); 
	 

}
