package com.cg.trg.boot.salon.service;

import com.cg.trg.boot.salon.bean.User;

public interface IUserService {
	public User signIn(User user);
	public User signOut(User user);
	public User changePassword(long id, String changePassword);
	public User updateCredentials(User user, String userName, String password);
	public User getUserById(long id);
}
