package com.payMyBuddy.service.Auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payMyBuddy.DAO.UserAppRepositry;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Models.Dto.UserAppDto;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
    private UserAppRepositry userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
        UserApp userApp = userRepository.findByEmail(email);
        if (userApp == null) {
            throw new UsernameNotFoundException("User not found with Email: " + email);
        }
        
        return new User(userApp.getEmail(),
        		        userApp.getPassword(), new ArrayList<>());
    }

    public UserApp save(UserAppDto userDto) {
    	UserApp user = new UserApp();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

}
