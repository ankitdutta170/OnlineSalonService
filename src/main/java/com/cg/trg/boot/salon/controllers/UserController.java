package com.cg.trg.boot.salon.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.trg.boot.salon.bean.User;
import com.cg.trg.boot.salon.exceptions.PasswordMismatchException;
import com.cg.trg.boot.salon.exceptions.UserNotFoundException;
import com.cg.trg.boot.salon.service.IUserServiceImpl;

import io.swagger.models.Response;

@RestController
@RequestMapping("user")

public class UserController {

	@Autowired
	IUserServiceImpl service;
	
	@GetMapping(value = "{signin}")
    public String signIn(@PathVariable("signIn") User user) {
		
		try {
			service.signIn(user);
			return "Sign In Successfull";
		}catch(UserNotFoundException ex) {
			System.out.println(ex.getMessage());
		}catch(PasswordMismatchException ex) {
			System.out.println(ex.getMessage());
			
		}
		return "SignIn Unsuccessfull";
		
		
        
       

    }
	
	@GetMapping(value = "{signout}")
    public String signOut(@PathVariable("signOut") User user) {
		
		if(service.signOut(user) != null) {
			return "Sign Out Successful.";
		}
		else {
			return "Sign Out Unsuccessful";
			
		}
	}

	 @PatchMapping(value = "{changedPassword}" )
	    public String changePassword(long id,@PathVariable("changedPassword") String changedPassword) {
		
			if(service.changePassword(id, changedPassword) != null)
				return "Password successfully changed.";
			else
				return "Unable to change password";
			
	    
	 }
}


