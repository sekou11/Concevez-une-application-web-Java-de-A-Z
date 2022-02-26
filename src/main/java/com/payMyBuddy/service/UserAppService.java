package com.payMyBuddy.service;

import java.util.List;
import java.util.Optional;

import com.payMyBuddy.Models.UserApp;

public interface UserAppService {
	public List<UserApp> getAllUserApp();

	public Optional<UserApp> getUserOpt(Integer id);

	public Integer saveUser(UserApp userApp);

	public UserApp findByEmail(String email);

	public void deleteByEmail(String email);

	public void deleteById(Integer id);

}
