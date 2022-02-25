package com.payMyBuddy.Models.Dto;

import com.payMyBuddy.Models.UserApp;

public class FriendDto {
	
	private UserApp user;
	private String friendEmail;
	public UserApp getUser() {
		return user;
	}
	public void setUser(UserApp user) {
		this.user = user;
	}
	public String getFriendEmail() {
		return friendEmail;
	}
	public void setFriendEmail(String friendEmail) {
		this.friendEmail = friendEmail;
	}

}
