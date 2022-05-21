package com.cognizant.cmg.api.users.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cmg.api.users.model.CreateUserRequestModel;
import com.cognizant.cmg.api.users.model.CreateUserResponseModel;
import com.cognizant.cmg.api.users.services.UsersService;
import com.cognizant.cmg.api.users.shared.UserDto;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	UsersService usersService;

	@GetMapping("/status/check")
	public String status() {
		return "Working";
	}

	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDeatils) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(userDeatils, UserDto.class);
		UserDto createdUser = usersService.createUser(userDto);
		
		CreateUserResponseModel responseUser = modelMapper.map(createdUser, CreateUserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
	}
}
