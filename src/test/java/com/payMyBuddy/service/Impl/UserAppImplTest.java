package com.payMyBuddy.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.payMyBuddy.DAO.UserAppRepositry;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.service.UserAppService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTests {

    private final UserApp user = new UserApp();
      private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserAppService userService;

    @Autowired
    private UserAppRepositry userRepository;

    @BeforeEach
    public void initUser() {

        user.setUserName("sekou");
        user.setEmail("sekou@gmail.com");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        
        user.setId(userService.saveUser(user));
    }

    @AfterEach
    public void cleanUser() {
        userRepository.deleteAll();
    }

    @Test
    public void testFindUserById() {
        Optional<UserApp> userFound = userService.getUserOpt(user.getId());
        assertEquals("John", userFound.get().getUserName());
    }

    @Test
    public void testFindAllUser() {
        List<UserApp> users = userService.getAllUserApp();
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    public void testDeleteUserById() {
        userService.deleteById(user.getId());
        assertFalse(userRepository.findById(user.getId()).isPresent());
    }

    @Test
    public void testDeleteUserByEmail() {
        userService.deleteByEmail("john@gmail.com");
        UserApp user = userRepository.findByEmail("john@gmail.com");
        assertNull(user);
    }

}
