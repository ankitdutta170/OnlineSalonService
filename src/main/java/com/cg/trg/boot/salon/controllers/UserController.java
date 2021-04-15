package com.cg.trg.boot.salon.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.User;
import com.cg.trg.boot.salon.dao.IUserRepository;
import com.cg.trg.boot.salon.exceptions.AppointmentNotFoundException;
import com.cg.trg.boot.salon.exceptions.PasswordMismatchException;
import com.cg.trg.boot.salon.exceptions.UserNotFoundException;
import com.cg.trg.boot.salon.service.IUserServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("user")

public class UserController {

	@Autowired
	IUserServiceImpl service;
	
	IUserRepository repository;
	@GetMapping(value = "/welcome/{signin}")
    public String signIn(@RequestBody User user) {
		
		try {
			service.signIn(user);
			return "Sign In Successfull";
		}catch(UserNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return "SignIn Unsuccessfull";
		
		
        
       

    }
	@GetMapping("/getUser/{uid}")
	public ResponseEntity<?> getUser(@PathVariable("uid")long id){
		User user = service.getUserById(id);
		if(user == null) {
			throw new UserNotFoundException("Appointment with appointment id:"+id+"not found");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
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
	 
	 @GetMapping("/")
		public String process(Model model, HttpSession session) {
			@SuppressWarnings("unchecked")
			List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

			if (messages == null) {
				messages = new ArrayList<>();
			}
			model.addAttribute("sessionMessages", messages);

			return "index";
		}

		@PostMapping("/persistMessage")
		public String persistMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
			@SuppressWarnings("unchecked")
			List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
			if (messages == null) {
				messages = new ArrayList<>();
				request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
			}
			messages.add(msg);
			request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
			return "redirect:/";
		}

		@PostMapping("/destroy")
		public String destroySession(HttpServletRequest request) {
			request.getSession().invalidate();
			return "redirect:/";
		}
		
		@PatchMapping("/{id}/{userName}/{password}")
		public String updateCredentials(@PathVariable("id")long userId,@PathVariable("userName") String userName,@PathVariable("password") String password) {
			User userToBeUpdated = service.getUserById(userId);
			
			
			User updatedUser = service.updateCredentials(userToBeUpdated, userName, password);
			if(updatedUser!=null) {
				return "Credentials successfully updated";
			}
			else
				return "Credentials failed to update";
			
			
		}
	}
	 
	 



