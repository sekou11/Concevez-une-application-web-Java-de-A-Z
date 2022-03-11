package com.payMyBuddy.Service.Impl;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
@SpringBootTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FriendServiceImplTest {
	
	private UserApp user1 = new UserApp() ;
	private UserApp user2 = new UserApp() ;
	
	private Friend friends = new Friend();
	@Autowired
	private FriendServiceImpl friendService;
	@Autowired
	private FriendRepository friendRepository;
	@Autowired
	private UserAppRespository userRepository;
	@Autowired
	private UserServiceImpl userService;

	 @BeforeAll
	    public void testSaveFriends() {
	        user1.setUserName("Ayu");
	        user1.setEmail("ayu@gmail.com");
	        user1.setPassword("123");
	    
	        user1.setId(userService.saveUser(user1));

	        user2.setUserName("Nil");
	        user2.setEmail("nil@gmail.com");
	        user2.setPassword("123");
	        user2.setId(userService.saveUser(user2));

	    }
	 
	 @AfterAll
	    public void clean() {
	       
//	        friendRepository.deleteAll();
//	        userRepository.deleteAll();
	    }

	@Test
	@Order(1)
	void testSave() {
		friends.setUser(user1);
		friends.setFriend(user2);
		friendService.save(friends);
	}
	
	@Test
	@Order(2)
	void testIsFriend() {
	   Integer friend= friendService.isFriend(user1.getId(), user2.getId());
	   assertEquals(1, friend);
	}

	@Test
    @Order(3)
    public void testFindAllByUserId() {
        List<Friend> friends = friendService.findAllByUser_Id(user1.getId());
        assertEquals(1, friends.size());
    }

    @Test
    @Order(4)
    public void testFindByFriendId() {
        Friend friends = friendService.findByFriend_Id(user2.getId());
        assertEquals("nil@gmail.com", friends.getFriend().getEmail());
    }

    @Test
    @Order(5)
    public void testFindAllMyFriends() {
        Set<UserApp> friends = friendService.findAllMyFriends(user1.getId());
        assertEquals(1, friends.size());
    }

    @Test
    @Order(6)
    public void testDeleteByFriendId() {
        Integer id = friendService.deleteByFriend_Id(user2.getId());
        assertNull(friendRepository.findByFriend_Id(id));
    }

}
