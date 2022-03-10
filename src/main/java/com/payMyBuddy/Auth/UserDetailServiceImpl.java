package com.payMyBuddy.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.payMyBuddy.DAO.UserAppRespository;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Models.dto.UserDto;

public class UserDetailServiceImpl {
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserAppRespository userRepository;
	
	  public UserApp save(UserDto userDto) {
		  UserApp user = new UserApp();
	        user.setUserName(userDto.getUserName());
	        user.setEmail(userDto.getEmail());
	        user.setPassword(encoder.encode(userDto.getPassword()));
	        return userRepository.save(user);
	    }

}
