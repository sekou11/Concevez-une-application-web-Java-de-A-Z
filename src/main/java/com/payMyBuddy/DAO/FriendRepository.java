package com.payMyBuddy.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.Models.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
	public Friend findByFriend_Id(int id);

	public List<Friend> findAllByUser_Id(int id);

	public Integer deleteByFriend_Id(int id);

	@Query("select count(distinct f.id) from Friend f where (f.friend.id = ?1 and f.user.id = ?2) OR (f.user.id = ?1 and f.friend.id = ?2)")
	 Integer isFriend(int id1, int id2);
  
}
