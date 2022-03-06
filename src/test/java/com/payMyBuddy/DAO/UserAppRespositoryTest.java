package com.payMyBuddy.DAO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.payMyBuddy.Models.UserApp;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserAppRespositoryTest {
	@Autowired
	private UserAppRespository repo;
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateUser() {
	    UserApp user = new UserApp();
	    user.setEmail("user1@email.com");
	    user.setPassword("123456");
	    user.setUserName("user1");
	     
	    UserApp savedUser = repo.save(user);
	     
	    UserApp existUser = entityManager.find(UserApp.class, savedUser.getId());
	     
	    assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
	     
	}
}
