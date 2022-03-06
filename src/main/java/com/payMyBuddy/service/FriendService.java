package com.payMyBuddy.Service;

import java.util.List;
import java.util.Set;

import com.payMyBuddy.Models.Friend;
import com.payMyBuddy.Models.UserApp;

public interface FriendService {
	 public void save(Friend friends);
	 public Friend findByFriend_Id(int id);
	 public List<Friend> findAllByUser_Id(int id);
	 public  Integer deleteByFriend_Id(int id);
	 public   Integer isFriend(int id1, int id2);
	 public  Set<UserApp> findAllMyFriends(int id);

}
