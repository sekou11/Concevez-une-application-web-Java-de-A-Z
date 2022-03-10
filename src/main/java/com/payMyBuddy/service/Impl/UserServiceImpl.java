package com.payMyBuddy.Service.Impl;

import java.util.List;
import java.util.Optional;

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
	private  UserAppRespository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();

	public UserServiceImpl(UserAppRespository userRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public UserServiceImpl(UserAppRespository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

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
	 public UserApp save(UserDto userDto) {
		  UserApp user = new UserApp();
	        user.setUserName(userDto.getUserName());
	        user.setEmail(userDto.getEmail());
	        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
	        return userRepository.save(user);
	    }
}

	


