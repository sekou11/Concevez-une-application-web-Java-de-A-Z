package com.payMyBuddy.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payMyBuddy.DAO.UserAppRepositry;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.service.UserAppService;
@Service
@Transactional
public class UserAppImpl implements UserAppService {

	private final UserAppRepositry userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
       @Autowired
	public UserAppImpl(UserAppRepositry userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public List<UserApp> getAllUserApp() {
		
		return userRepository.findAll();
				}

	@Override
	public Optional<UserApp> getUserOpt(Integer id) {
		return userRepository.findById(id);
		 
	}

	@Override
	public Integer saveUser(UserApp userApp) {
		  // we will use spring security for save userDto after registration
		 if (userApp !=null) {
			 UserApp user = new UserApp();
				user.setUserName(user.getUserName());
				user.setEmail(user.getEmail());
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				return userRepository.save(user).getId();
		}
		
		
		return null;
	}

	@Override
	public UserApp findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public void deleteByEmail(String email) {
		userRepository.deleteByEmail(email);

	}

	@Override
	public void deleteById(Integer id) {
	userRepository.deleteById(id);

	}

}
