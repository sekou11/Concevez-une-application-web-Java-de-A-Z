package com.payMyBuddy.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.Models.UserApp;

@Repository
public interface UserAppRespository extends JpaRepository<UserApp, Integer> {
//    @Query("SELECT u FROM UserApp u WHERE u.email = ?1")
	 public UserApp findByEmail(String email);

	 public void deleteByEmail(String email);

}