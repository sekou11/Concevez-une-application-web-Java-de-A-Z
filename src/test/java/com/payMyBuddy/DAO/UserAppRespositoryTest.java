package com.payMyBuddy.DAO;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.payMyBuddy.Models.UserApp;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)

public class UserAppRespositoryTest {
	@Autowired
	private UserAppRespository repo;
	

	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateUser() {
	       
		 UserApp savedUser = repo.save(new UserApp("user1", "user1@email.com", "123456"));
	     assertThat(savedUser.getId()).isGreaterThan(0);

	}
	
	@Test
	@Rollback(false)
	@Order(2)
	public void testFindUserByEmail() {
	       
	     UserApp userEmail = repo.findByEmail("user1@email.com");
	     assertThat(userEmail.getEmail()).isEqualTo("user1@email.com");
	}
	
	@Test
	@Rollback(false)
	@Order(3)
	public void testListUser() {
	       
	    List<UserApp>users = repo.findAll();
	    assertThat(users.size()).isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	@Order(4)
	public void testDeleteUserById() {
		UserApp user = repo.findByEmail("user1@email.com");
		 
		 repo.deleteById(user.getId());
		UserApp userDeleted = repo.findByEmail("user1@email.com");
		
	     assertThat(userDeleted).isNull();
	   
	}
	
	
	@Test
	@Rollback(false)
	@Order(5)
	public void testReCreateNewUser() {
	       
	     UserApp savednewUser = repo.save(new UserApp("user2", "user2@email.com", "123456"));
	     assertThat(savednewUser.getId()).isGreaterThan(0);
	}
	
	
	@Test
	@Rollback(false)
	@Order(6)
	public void testDeleteUserByEmail() {
		UserApp user = repo.findByEmail("user2@email.com");
		 
		 repo.deleteByEmail(user.getEmail());
		UserApp userDeleted = repo.findByEmail("user1@email.com");
		
	     assertThat(userDeleted).isNull();
	   
	}
}
