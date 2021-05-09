package com.cg.trg.boot.salon.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.User;
import com.cg.trg.boot.salon.dao.IUserRepository;
import com.cg.trg.boot.salon.exceptions.PasswordMismatchException;
import com.cg.trg.boot.salon.exceptions.UserNotFoundException;
@Service
public class IUserServiceImpl implements IUserService {

	@Autowired
	IUserRepository repository;
	
	@Override
	public User signIn(User user) {
		// TODO Auto-generated method stub
		User loggedInUser = repository.getUserByUsernameAndPassword(user.getUserName(), user.getPassword());
		if(loggedInUser != null)
			return loggedInUser;
		throw new UserNotFoundException("User not found in database");
	
		
	}

	

	@Override
	public User changePassword(long id, String changedPassword) {
		// TODO Auto-generated method stub
		
			if(repository.existsById(id)) {
			
			User userFromDBTable = repository.findById(id).get();
			userFromDBTable.setPassword(changedPassword);
			return userFromDBTable;
		
		}
		else {
			return null;
		}
			
		
	}

	@Override
	public User updateCredentials(User user,String userName, String password) {
		user.setUserName(userName);
		user.setPassword(password);
		repository.save(user);
		return user;
	}

	@Override
	public User getUserById(long id) {
		Optional<User> user = repository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		else {
			return null;
		}
	}



	@Override
	public User signOut(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	

	
}
