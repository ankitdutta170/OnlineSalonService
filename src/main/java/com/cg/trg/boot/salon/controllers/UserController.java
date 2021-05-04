package com.cg.trg.boot.salon.controllers;



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
import org.springframework.web.bind.annotation.RequestBody;
import com.cg.trg.boot.salon.bean.User;
import com.cg.trg.boot.salon.dao.IUserRepository;
import com.cg.trg.boot.salon.exceptions.InvalidUserException;
import com.cg.trg.boot.salon.exceptions.UserNotFoundException;
import com.cg.trg.boot.salon.service.IUserServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("user")

public class UserController {

	@Autowired
	IUserServiceImpl service;

	IUserRepository repository;

	@GetMapping(value = "/signin")
	public ResponseEntity<String> signIn(@RequestBody User user, HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		session.setAttribute("userName", "Tom@gmailcom");
		session.setAttribute("password", "Tom");
		try {

			service.signIn(user);
			return new ResponseEntity<String>("Login Successfull!", HttpStatus.OK);
		} catch (UserNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return new ResponseEntity<String>("Login Unsuccessfull!", HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/getUser/{uid}")

	public ResponseEntity<?> getUser(@PathVariable("uid") long id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");

		User user = service.getUserById(id);
		if (user == null) {
			throw new UserNotFoundException("Appointment with appointment id:" + id + "not found");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("signout")
	public ResponseEntity<String> signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();

		session.invalidate();
		return new ResponseEntity<String>("Logout Successfull!", HttpStatus.OK);

	}


	@PatchMapping("/{id}/{userName}/{password}")
	public ResponseEntity<?> updateCredentials(@PathVariable("id") long userId, @PathVariable("userName") String userName,
			@PathVariable("password") String password,HttpServletRequest request) {
		User user = checkUserLoggedIn(request);
		if(!user.getRole().equals("admin"))
			throw new InvalidUserException("Invalid Operation");
		
		User userToBeUpdated = service.getUserById(userId);

		User updatedUser = service.updateCredentials(userToBeUpdated, userName, password);
		if (updatedUser != null) {
			return new ResponseEntity<String>("Credentials updated successfully", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Not able to update credentials", HttpStatus.NOT_FOUND);

	}
	
	public User checkUserLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			throw new InvalidUserException("You should login first");
		}
		String userName = (String) session.getAttribute("name");
		User user = repository.findByUserName(userName);
		return user;
				
	}

}
