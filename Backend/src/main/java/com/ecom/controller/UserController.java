package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.user.AuthRequestDto;
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
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(registerUserDetails));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	/*
	 * Desc - user sign in
	 * URL - http://host:port/users/signin
	 * Method - POST 
	 * Payload - user req dto 
	 * Success resp - Api resp 
	 * err - Api resp err mesg
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> signinUser(@RequestBody AuthRequestDto loginDto) {

		try {
			return ResponseEntity.ok(userService.signInUser(loginDto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		
	}
	
	

}
