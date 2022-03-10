package com.payMyBuddy.Service;

import java.util.List;
import java.util.Optional;

import com.payMyBuddy.Models.UserApp;

public interface UserService {
	public List<UserApp> findAll();

	public Optional<UserApp> findById(int id);

	public UserApp findByEmail(String email);

	
	public void deleteById(int id);

	public void deleteByEmail(String email);

	public Integer save(UserApp user);
}
