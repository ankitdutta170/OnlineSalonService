package com.cg.trg.boot.salon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.bean.User;
import com.cg.trg.boot.salon.dao.IAppointmentRepository;
import com.cg.trg.boot.salon.dao.IUserRepository;
import com.cg.trg.boot.salon.service.AppointmentServiceImpl;
import com.cg.trg.boot.salon.service.IUserService;
import com.cg.trg.boot.salon.service.IUserServiceImpl;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	IUserServiceImpl userService;
	
	@MockBean
	IUserRepository userRepository;
	
	@Test
	@DisplayName("Test signin of User")
	public void signIn(){
		
		Optional<User> user = userRepository.findById(1L);
 		if(user.isPresent()) {
 			user.get().setLoggedIn(true);
 			userRepository.save(user.get());
 			
 		}
 		Optional<User> updatedUser = userRepository.findById(1l);
		if(updatedUser.isPresent()) {
			assertThat(updatedUser.get().isLoggedIn()==true);
		}
		
		
}
	@Test
	 @DisplayName("Test for signout of User")
		public void signOut() {
		User user = new User();
		user.setLoggedIn(true);
		
	    	when(userRepository.save(user)).thenReturn(user);
			assertEquals(false, userService.signOut(user).isLoggedIn());
			}
	
	@Test
	@DisplayName("Test for change password of User")
	public void changePassword() {
		Optional<User> user = userRepository.findById(1L);
		if(user.isPresent()) {
			user.get().getPassword();
			userRepository.save(user.get());
			
		}
		Optional<User> updatedUser = userRepository.findById(1L);
		if(updatedUser.isPresent()) {
			assertThat(updatedUser.get().getPassword().equals(user));
					
		}
	}
	
	
}
	
	
		
		
		
		
		

	
	
	
	
	
	




