package com.cognizant.cmg.api.users.services;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.cmg.api.users.data.UserEntity;
import com.cognizant.cmg.api.users.data.UsersRepo;
import com.cognizant.cmg.api.users.shared.UserDto;

@Service
public class UsersServiceImpl implements UsersService {

	UsersRepo usersRepo;
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UsersServiceImpl(UsersRepo usersRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usersRepo = usersRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDto createUser(UserDto userDetails) {
		// TODO Auto-generated method stub

		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncyptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

		usersRepo.save(userEntity);

		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		UserEntity userEntity = usersRepo.findByUserName(username);

		if (userEntity == null) {
			throw new UsernameNotFoundException(username);

		}

		return new User(userEntity.getUserName(), userEntity.getEncyptedPassword(), true, true, true, true,
				new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByUserName(String username) {
		UserEntity userEntity = usersRepo.findByUserName(username);

		if (userEntity == null)
			throw new UsernameNotFoundException(username);
		
		return new ModelMapper().map(userEntity, UserDto.class);
	}

}
