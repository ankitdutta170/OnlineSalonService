package com.cg.trg.boot.salon.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.trg.boot.salon.bean.User;
import com.cg.trg.boot.salon.dao.IUserRepository;
@Service
public class IUserServiceImpl implements IUserService {

	@Autowired
	IUserRepository repository;
	
	@Override
	public User signIn(User user) {
		// TODO Auto-generated method stub
		if (repository.existsById(user.getUserId())) {

			User userFromDBTable = repository.findById(user.getUserId()).get();
			if (user.getPassword().equals(userFromDBTable.getPassword())) {
				user.setLoggedIn(true);
				return userFromDBTable;
				
			}
				
			return null;
		} else {
			return null;
		}

	}

	@Override
	public User signOut(User user) {
		// TODO Auto-generated method stub
		
		if(user.isLoggedIn()) {
			user.setLoggedIn(false);
			return user;
		}
		
		return null;
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

	

	
	

	
}
