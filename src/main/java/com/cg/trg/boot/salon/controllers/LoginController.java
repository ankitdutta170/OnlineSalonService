package com.cg.trg.boot.salon.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.trg.boot.salon.bean.LoginCredentials;
import com.cg.trg.boot.salon.bean.User;
import com.cg.trg.boot.salon.dao.IUserRepository;
import com.cg.trg.boot.salon.exceptions.InvalidUserException;
import com.google.common.base.Optional;

@RestController
public class LoginController {
	
	@Autowired
	IUserRepository userRepository;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginCredentials credentials, HttpServletRequest request){
		
		User user =  userRepository.findByUserName(credentials.getUserName());
		if(user == null)
			throw new InvalidUserException("User with "+user.getUserName()+"not found");
		if(!user.getPassword().equals(credentials.getPassword())) {
			throw new InvalidUserException("Invalid Password");
		}
		HttpSession session = request.getSession();
		session.setAttribute("name", user.getUserName());
		session.setAttribute("role", user.getRole());
		
		return "Login Successfull";
		
	}
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session == null) {
			throw new InvalidUserException("User not logged in");
		}
		session.invalidate();
		return "User successfully logged out";
	}

}
