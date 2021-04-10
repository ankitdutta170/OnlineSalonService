package com.cg.trg.boot.salon.service;

<<<<<<< Updated upstream
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
=======
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
>>>>>>> Stashed changes

import com.cg.trg.boot.salon.bean.User;
import com.cg.trg.boot.salon.dao.IUserRepository;

public class IUserServiceImpl implements IUserService {

	@Autowired
	IUserRepository repository;
<<<<<<< Updated upstream
	
	@Override
	public User signIn(User user) {
		// TODO Auto-generated method stub
=======

	@Override
	public User signIn(User user) {
		// TODO Auto-generated method stub

>>>>>>> Stashed changes
		if (repository.existsById(user.getUserId())) {

			User userFromDBTable = repository.findById(user.getUserId()).get();
			if (user.getPassword().equals(userFromDBTable.getPassword())) {
				user.setLoggedIn(true);
				return userFromDBTable;
<<<<<<< Updated upstream
				
=======
>>>>>>> Stashed changes
			}
				
			return null;
		} else {
			return null;
		}

	}

	@Override
	public User signOut(User user) {
<<<<<<< Updated upstream
		// TODO Auto-generated method stub
=======
>>>>>>> Stashed changes
		
		if(user.isLoggedIn()) {
			user.setLoggedIn(false);
			return user;
		}
<<<<<<< Updated upstream
		
=======
			

>>>>>>> Stashed changes
		return null;
	}

	@Override
<<<<<<< Updated upstream
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

	

	
	

	
=======
	public User changePassword(long id, User user) {
		// TODO Auto-generated method stub
		
		if(repository.existsById(id)) {
			
			User userFromDBTable = repository.findById(id).get();
			userFromDBTable.setPassword(user.getPassword());
			return userFromDBTable;
		
		}
		else 
			return null;


	}
>>>>>>> Stashed changes
}
