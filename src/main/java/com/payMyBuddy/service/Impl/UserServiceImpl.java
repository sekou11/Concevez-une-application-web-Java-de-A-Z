package com.payMyBuddy.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payMyBuddy.DAO.UserAppRespository;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Models.dto.UserDto;
import com.payMyBuddy.Service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserAppRespository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<UserApp> findAll() {

		return userRepository.findAll();
	}

	@Override
	public Optional<UserApp> findById(int id) {

		return userRepository.findById(id);
	}

	@Override
	public UserApp findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	@Override
	public void deleteById(int id) {
		userRepository.deleteById(id);

	}

	@Override
	public void deleteByEmail(String email) {
		userRepository.findByEmail(email);

	}

	@Override
	public UserApp saveDto(UserDto userDto) {
		UserApp user = new UserApp();
		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Integer saveUser(UserApp user) {
		 if (user != null) {
	           UserApp newUser = new UserApp();
	            newUser.setUserName(user.getUserName());
	            newUser.setEmail(user.getEmail());
	            newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	            return userRepository.save(newUser).getId();
	        }
	        return null;
	}
}
