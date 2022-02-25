package com.payMyBuddy.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.Models.UserApp;
@Repository
public interface UserAppRepositry extends JpaRepository<UserApp, Integer> {
  public UserApp findByEmail(String email);
  public void deleteByEmail(String email);
}
