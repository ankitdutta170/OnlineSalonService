package com.cg.trg.boot.salon.dao;

<<<<<<< Updated upstream




=======
>>>>>>> Stashed changes
import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.trg.boot.salon.bean.User;

<<<<<<< Updated upstream
public interface IUserRepository extends JpaRepository<User, Long>{
=======
public interface IUserRepository extends JpaRepository<User, Long> {
>>>>>>> Stashed changes
	public User signIn(User user);
	public User signOut(User user);
	public User changePassword(long id, User user);
	

}
