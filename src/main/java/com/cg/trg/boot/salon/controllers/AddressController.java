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
import com.cg.trg.boot.salon.bean.Billing;
import com.cg.trg.boot.salon.exceptions.AddressNotFoundException;
import com.cg.trg.boot.salon.exceptions.AppointmentNotFoundException;
import com.cg.trg.boot.salon.exceptions.BillNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.exceptions.InvalidUserException;
import com.cg.trg.boot.salon.jwt.JwtTokenUtil;
import com.cg.trg.boot.salon.service.AddressServiceImpl;
import com.cg.trg.boot.salon.service.ValidateToken;

import io.jsonwebtoken.SignatureException;

@RestController
@RequestMapping("address")
@CrossOrigin(origins = "http://localhost:4200")
public class AddressController {
	@Autowired
	 AddressServiceImpl service;
	
	@Autowired
	ValidateToken login;
	
	@PostMapping
	public ResponseEntity<String>saveAddress(@RequestBody Address address, HttpServletRequest request) {
		
		login.validateToken(request,"customer");
		Address addres = service.addAddress(address);
		if(addres != null) {
			return new ResponseEntity<String>("Address saved successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Address failed to save", HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("{aid}")
	public ResponseEntity<String> removeAddress(@PathVariable("aid") long id,HttpServletRequest request) {
		login.validateToken(request,"customer");
		Address deleteAddress = service.removeAddress(id);
		if(deleteAddress != null) {
			return new ResponseEntity<String>("Address successfully deleted", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Address failed to delete", HttpStatus.BAD_REQUEST);
	}
	@PutMapping("{aid}")
	public ResponseEntity<String> updateAddress(@PathVariable("aid") long id,@RequestBody Address address,HttpServletRequest request) {
		login.validateToken(request,"customer");
		
		Address updatedAddress = service.updateAddress(id, address);
		if(updatedAddress != null) {
			return new ResponseEntity<String>("Address successfully updated", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Address failed to delete", HttpStatus.BAD_REQUEST);
	}
	@PutMapping
	public String updateAddress( @RequestBody Address address,HttpServletRequest request) {
		login.validateToken(request,"customer");
		if (service.update(address))
			return "Bill data successfully updated";
		else
			throw new AppointmentNotFoundException("Update", "Appointment with Id " + address.getAddressId() + " to update not found");
	}
	@GetMapping("/{aid}")
	public ResponseEntity<?> getAddress(@PathVariable("aid")long id,HttpServletRequest request){
		
		login.validateToken(request,"customer");
		Address address = service.getAddressDetails(id);
		if(address == null) {
			throw new AddressNotFoundException("Request", "Address with Adress id:"+id+"not found");
		}
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<Address>> getAllAddress(HttpServletRequest request){
		login.validateToken(request,"customer");
		List<Address> address = service.getAllAddress();
		if(address.size() == 0) {
			throw new EmptyDataException("No Address saved in database");
		}
		return new ResponseEntity<List<Address>>(address, HttpStatus.OK);

		
	}
	
	

}
