package com.payMyBuddy.service;

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

import com.payMyBuddy.DAO.UserAppRespository;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Service.UserService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTests {

    private final UserApp user = new UserApp();

    @Autowired
    private UserService userService;

    @Autowired
    private UserAppRespository userRepository;

    @BeforeEach
    public void initUser() {

        user.setUserName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("456");

        user.setId(userService.save(user));
    }

    @AfterEach
    public void cleanUser() {
        userRepository.deleteAll();
    }

    @Test
    public void testFindUserById() {
        Optional<UserApp> userFound = userService.findById(user.getId());
        assertEquals("John", userFound.get().getUserName());
    }

    @Test
    public void testFindAllUser() {
        List<UserApp> users = userService.findAll();
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
