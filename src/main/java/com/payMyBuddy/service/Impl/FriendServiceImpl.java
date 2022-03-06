package com.payMyBuddy.Service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payMyBuddy.DAO.FriendRepository;
import com.payMyBuddy.DAO.UserAppRespository;
import com.payMyBuddy.Models.Friend;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Service.FriendService;
@Service
@Transactional
public class FriendServiceImpl implements FriendService {

	private final FriendRepository friendsRepository;
    private final UserAppRespository userRepository;

    @Autowired
    public FriendServiceImpl(FriendRepository friendsRepository, UserAppRespository userRepository) {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Friend friends) {
        friendsRepository.save(friends);
    }

    @Override
    public Friend findByFriend_Id(int id) {
        return friendsRepository.findByFriend_Id(id);
    }

    @Override
    public List<Friend> findAllByUser_Id(int id) {
        return friendsRepository.findAllByUser_Id(id);
    }

    @Override
    public Integer deleteByFriend_Id(int id) {
        return friendsRepository.deleteByFriend_Id(id);
    }

    @Override
    public Integer isFriend(int id1, int id2) {
        Integer friend = 0;
        Optional<UserApp> user1 = userRepository.findById(id1);
        Optional<UserApp> user2 = userRepository.findById(id2);
        if (user1.isPresent() && user2.isPresent()) {
            friend = friendsRepository.isFriend(id1, id2);

        }
        return friend;
    }

    
    @Override
    public Set<UserApp> findAllMyFriends(int id) {
        HashSet<UserApp> friendsSet = new HashSet<>();
        Optional<UserApp> user = userRepository.findById(id);
        List<Friend> friends = friendsRepository.findAll();
        if (user.isPresent()) {
            for (Friend friend : friends) {
                // Others add me as friend:
                if (friend.getFriend().getId() == id) {
                    friendsSet.add(friend.getUser());
                    // I add other as friend:
                } else if (friend.getUser().getId() == id) {
                    friendsSet.add(friend.getFriend());
                }
            }
        }
        return friendsSet;
    }
}
