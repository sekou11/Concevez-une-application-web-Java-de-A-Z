package com.payMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.payMyBuddy.DAO.FriendRepository;
import com.payMyBuddy.DAO.UserAppRespository;
import com.payMyBuddy.Models.Friend;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Service.FriendService;
import com.payMyBuddy.Service.UserService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FriendsServiceTests {

    private UserApp user1 = new UserApp();
    private UserApp user2 = new UserApp();

    private Friend friends = new Friend();

    @Autowired
    private FriendService friendsService;

    @Autowired
    private FriendRepository friendsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAppRespository userRepository;

    @BeforeAll
    public void testSaveFriends() {
        user1.setUserName("Ayu");
        user1.setEmail("ayu@gmail.com");
        user1.setPassword("123");
       
        user1.setId(userService.save(user1));

        user2.setUserName("Nil");
        user2.setEmail("nil@gmail.com");
        user2.setPassword("123");
        user2.setId(userService.save(user2));

    }

    @AfterAll
    public void clean() {
       
        friendsRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    @Order(1)
    public void testSaveFriend() {
        friends.setUser(user1);
        friends.setFriend(user2);
        friendsService.save(friends);
    }

    @Test
    @Order(2)
    public void testIsFriend() {
        Integer friend = friendsService.isFriend(user1.getId(), user2.getId());
        assertEquals(1, friend);
    }

    @Test
    @Order(3)
    public void testFindAllByUserId() {
        List<Friend> friends = friendsService.findAllByUser_Id(user1.getId());
        assertEquals(1, friends.size());
    }

    @Test
    @Order(4)
    public void testFindByFriendId() {
        Friend friends = friendsService.findByFriend_Id(user2.getId());
        assertEquals("nil@gmail.com", friends.getFriend().getEmail());
    }

    @Test
    @Order(5)
    public void testFindAllMyFriends() {
        Set<UserApp> friends = friendsService.findAllMyFriends(user1.getId());
        assertEquals(1, friends.size());
    }

    @Test
    @Order(6)
    public void testDeleteByFriendId() {
        Integer id = friendsService.deleteByFriend_Id(user2.getId());
        assertNull(friendsRepository.findByFriend_Id(id));
    }

}
