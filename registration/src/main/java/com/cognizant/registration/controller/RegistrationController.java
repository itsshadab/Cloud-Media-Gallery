package com.cognizant.registration.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.registration.model.request.Users;
import com.cognizant.registration.model.request.UsersRepo;

@RestController
public class RegistrationController {

	@Autowired
	UsersRepo usersRepo;

	@PostMapping("/register")
	public ResponseEntity<Users> registerUser(@Valid @RequestBody Users usersModel){
		
		Users user = new Users();
		user.setFirstName(usersModel.getFirstName());
		user.setLastName(usersModel.getLastName());
		user.setUserName(usersModel.getUserName());
		user.setEmailId(usersModel.getEmailId());
		user.setPassword(usersModel.getPassword());
		
		user = usersRepo.save(user);
		
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}

}
