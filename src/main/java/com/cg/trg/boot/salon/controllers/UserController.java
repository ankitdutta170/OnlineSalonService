package com.cg.trg.boot.salon.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.trg.boot.salon.bean.User;
import com.cg.trg.boot.salon.exceptions.PasswordMismatchException;
import com.cg.trg.boot.salon.exceptions.UserNotFoundException;
import com.cg.trg.boot.salon.service.IUserServiceImpl;


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
	 
	 @PostMapping("/addNote")
	    public String addNote(@RequestParam("note") String note, HttpServletRequest request) {
	        //get the notes from request session
	        List<String> notes = (List<String>) request.getSession().getAttribute("NOTES_SESSION");
	        //check if notes is present in session or not
	        if (notes == null) {
	            notes = new ArrayList<>();
	            // if notes object is not present in session, set notes in the request session
	            request.getSession().setAttribute("NOTES_SESSION", notes);
	        }
	        notes.add(note);
	        request.getSession().setAttribute("NOTES_SESSION", notes);
	        return "redirect:/home";
	    }
	    @GetMapping("/home")
	    public String home(Model model, HttpSession session) {
	        List<String> notes = (List<String>) session.getAttribute("NOTES_SESSION");
	        model.addAttribute("notesSession", notes!=null? notes:new ArrayList<>());
	        return "home";
	    }
	    @PostMapping("/invalidate/session")
	    public String destroySession(HttpServletRequest request) {
	        //invalidate the session , this will clear the data from configured database (Mysql/redis/hazelcast)
	        request.getSession().invalidate();
	        return "redirect:/home";
	    }
	}
	 
	 



