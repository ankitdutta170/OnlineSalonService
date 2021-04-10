package com.cg.trg.boot.salon.dao;





import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.trg.boot.salon.bean.User;

public interface IUserRepository extends JpaRepository<User, Long>{
	public User signIn(User user);
	public User signOut(User user);
	public User changePassword(long id, User user);
	

}
