package com.cognizant.cmg.api.users.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cognizant.cmg.api.users.shared.UserDto;

public interface UsersService extends UserDetailsService{
	UserDto createUser(UserDto userDetails);

	UserDto getUserDetailsByUserName(String username);

}
