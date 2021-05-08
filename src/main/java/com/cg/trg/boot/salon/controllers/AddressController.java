package com.cg.trg.boot.salon.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.trg.boot.salon.bean.Address;
import com.cg.trg.boot.salon.exceptions.AddressNotFoundException;
import com.cg.trg.boot.salon.exceptions.BillNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.service.AddressServiceImpl;

@RestController
@RequestMapping("address")
@CrossOrigin(origins = "http://localhost:4200")
public class AddressController {
	@Autowired
	 AddressServiceImpl service;
	
	@PostMapping
	public ResponseEntity<String>saveAddress(@RequestBody Address address, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		
		Address addres = service.addAddress(address);
		if(addres != null) {
			return new ResponseEntity<String>("Address saved successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Address failed to save", HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("{aid}")
	public ResponseEntity<String> removeAddress(@PathVariable("aid") long id,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		
		Address deleteAddress = service.removeAddress(id);
		if(deleteAddress != null) {
			return new ResponseEntity<String>("Address successfully deleted", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Address failed to delete", HttpStatus.BAD_REQUEST);
	}
	@PutMapping("{aid}")
	public ResponseEntity<String> updateAddress(@PathVariable("aid") long id,@RequestBody Address address,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		
		Address updatedAddress = service.updateAddress(id, address);
		if(updatedAddress != null) {
			return new ResponseEntity<String>("Address successfully updated", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Address failed to delete", HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/{aid}")
	public ResponseEntity<?> getAddress(@PathVariable("aid")long id,HttpServletRequest request){
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		
		Address address = service.getAddressDetails(id);
		if(address == null) {
			throw new AddressNotFoundException("Request", "Address with Adress id:"+id+"not found");
		}
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<Address>> getAllAddress(HttpServletRequest request){
		
		List<Address> address = service.getAllAddress();
		if(address.size() == 0) {
			throw new EmptyDataException("No Address saved in database");
		}
		return new ResponseEntity<List<Address>>(address, HttpStatus.OK);

		
	}

}
