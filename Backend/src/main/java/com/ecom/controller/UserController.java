package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.user.RegisterDto;
import com.ecom.services.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;

	/*
	 * Desc - user sign up
	 * URL - http://host:port/users/signup
	 * Method - POST
	 * Payload - user req dto
	 * Success resp - Api resp
	 * err - Api resp err mesg
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerUserDetails) {
		return userService.registerUser(registerUserDetails);
	}

}
