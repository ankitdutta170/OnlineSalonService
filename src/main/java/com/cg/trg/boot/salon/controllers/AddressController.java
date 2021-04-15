package com.cg.trg.boot.salon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.trg.boot.salon.bean.Address;
import com.cg.trg.boot.salon.exceptions.BillNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.service.AddressServiceImpl;
@CrossOrigin
@RestController
@RequestMapping("address")
public class AddressController {
	@Autowired
	 AddressServiceImpl service;
	@PostMapping
	public String saveAddress(Address address) {
		Address addres = service.addAddress(address);
		if(addres != null) {
			return "Address successfully made";
		}
		else
			return "Failed to give Address";
	}
	@DeleteMapping("{aid}")
	public String removeAddress(@PathVariable("aid") long id) {
		Address deleteAddress = service.removeAddress(id);
		if(deleteAddress != null) {
			return "Address successfully deleted";
		}
		else
			return "Address failed to delete";
	}
	@PutMapping
	public String updateAddress(long id, Address address) {
		Address updatedAddress = service.updateAddress(id, address);
		if(updatedAddress != null) {
			return "Address succesfully updated";
		}
		else
			return "Address failed to update";
	}
	@GetMapping("{aid}")
	public ResponseEntity<?> getAddress(@PathVariable("aid")long id){
		Address address = service.getAddressDetails(id);
		if(address == null) {
			throw new BillNotFoundException("Request", "Address with Adress id:"+id+"not found");
		}
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}
	@GetMapping
	public List<Address> getAllAddress(){
		List<Address> address = service.getAllAddress();
		if(address.size() == 0) {
			throw new EmptyDataException("No Address saved in database");
		}
		return address;
		
	}

}
