package com.cg.trg.boot.salon.controllers;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	}
	 
	 



