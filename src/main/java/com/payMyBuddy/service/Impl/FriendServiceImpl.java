package com.payMyBuddy.service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payMyBuddy.DAO.FriendRepositry;
import com.payMyBuddy.DAO.UserAppRepositry;
import com.payMyBuddy.Models.Friend;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.service.FriendService;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {

	private final FriendRepositry friendRepositry;
	private final UserAppRepositry userRepositry;

	@Autowired
	public FriendServiceImpl(FriendRepositry friendRepositry, UserAppRepositry userRepositry) {
		super();
		this.friendRepositry = friendRepositry;
		this.userRepositry = userRepositry;
	}

	@Override
	public Friend SaveFriend(Friend friend) {
		// TODO Auto-generated method stub
		return friendRepositry.save(friend);
	}

	@Override
	public Friend findFriendById(Integer id) {
		// TODO Auto-generated method stub
		return friendRepositry.findByFriend_Id(id);
	}

	@Override
	public Integer deleteByFriend_Id(int id) {
		// TODO Auto-generated method stub
		return friendRepositry.deleteByFriend_Id(id);
	}

	@Override
	public List<Friend> findAllByUser_Id(int id) {
		// TODO Auto-generated method stub
		return friendRepositry.findAllByUser_Id(id);
	}

	@Override
	public Integer isFriend(int id1, int id2) {
		Integer connection = 0;
		Optional<UserApp> user1 = userRepositry.findById(id1);
		Optional<UserApp> user2 = userRepositry.findById(id2);
		if (user1.isPresent() && user2.isPresent()) {
			connection = friendRepositry.isFriend(id1, id2);
		}
		return connection;
	}

	// find all friends of user
	@Override
	public Set<UserApp> findAllMyFriends(int id) {
		Set<UserApp> userAppSet = new HashSet<UserApp>();
		Optional<UserApp> userApp = userRepositry.findById(id);
		List<Friend> allFriends = friendRepositry.findAll();
		if (userApp.isPresent()) {
			for (Friend friend : allFriends) {
				// add user has a friend
				if (friend.getFriend().getId() == id) {
					userAppSet.add(friend.getUser());
				} else if (friend.getUser().getId() == id) {
					userAppSet.add(friend.getFriend());
				}
			}
		}
		return userAppSet;
	}

}
