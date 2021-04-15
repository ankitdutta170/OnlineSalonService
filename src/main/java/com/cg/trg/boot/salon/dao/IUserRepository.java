package com.cg.trg.boot.salon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.trg.boot.salon.bean.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	
	/*@Query("select user from User user where username = :userName and password=:password")
	public User getUserByUsernameAndPassword(@Param("userName") String userName,@Param("password") String password);
	*/
	
	/*
	 * public User signIn(User user); public User signOut(User user); public User
	 * changePassword(long id, User user);
	 */

}
