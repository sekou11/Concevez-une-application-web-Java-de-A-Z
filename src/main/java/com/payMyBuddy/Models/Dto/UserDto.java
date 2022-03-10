package com.payMyBuddy.Models.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class UserDto {
    private String userName;
    private String email;
    private String password;
	public UserDto(String userName, String email, String password) {
		
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public UserDto() {
		// TODO Auto-generated constructor stub
	}

   
	
}
