package com.ecom.controller;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.AuthResponse;
import com.ecom.dtos.address.AddressResponseDto;
import com.ecom.dtos.address.UserAddressDto;
import com.ecom.dtos.user.AuthRequestDto;
import com.ecom.dtos.user.ChangeUserPasswordDto;
import com.ecom.dtos.user.RegisterDto;
import com.ecom.dtos.user.UserDetailsDto;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.security.CustomUserDetailsImpl;
import com.ecom.security.JwtUtils;
import com.ecom.services.IAddressService;
import com.ecom.services.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	
	@Autowired
	private IUserService userService;

	@Autowired
	private IAddressService addressService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	// Desc - user sign up
	// URL - http://host:port/users/signup
	// Method - POST
	// Payload - user req dto
	// Success resp - Api resp
	// err - Api resp err mesg
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerUserDetails) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(registerUserDetails));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	// Desc - user sign in
	// URL - http://host:port/users/signin
	// Method - POST
	// Payload - user req dto
	// Success resp - Api resp
	// err - Api resp err mesg
	@PostMapping("/signin")
	public ResponseEntity<?> signinUser(@RequestBody AuthRequestDto loginDto) {

//		try {
//			return ResponseEntity.ok(userService.signInUser(loginDto));
//		} catch (RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//		}

		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
					loginDto.getPassword());

			Authentication authToken = authenticationManager.authenticate(token);
			CustomUserDetailsImpl user = (CustomUserDetailsImpl) authToken.getPrincipal();
			return ResponseEntity.status(HttpStatus.OK)
					.body(new AuthResponse("Successfully Logged in", user.getUser().getFirstName(),
							user.getUser().getUserRole().name(), jwtUtils.generateJwtToken(authToken)));
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
		}

	}

	// Desc -get user details
	// URL - http://host:port/users/userDetails
	// Method - GET
	// Header - email
	// URL Query Parameter - none
	// Payload - none
	// Success resp - ResponseEntity with UserDetailsDto
	// err - ResponseEntity with err mesg
	@GetMapping("/user_details")
	public ResponseEntity<?> getUserDetails() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return ResponseEntity.ok(userService.getUserDetails(authentication.getName()));
		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	// Desc - update user details
	// URL - http://host:port/users/update
	// Method - PUT
	// Payload - user req dto
	// Success resp - ResponseEntity with success message
	// err - ResponseEntity with err mesg
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UserDetailsDto updateUserDetails) {

		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return ResponseEntity.ok(userService.updateUser(updateUserDetails, authentication.getName()));
		} catch (RuntimeErrorException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// Desc - change user password
	// URL - http://host:port/users/change_password
	// Method - PUT
	// Payload - user password dto
	// Success resp - ResponseEntity with success message
	// err - ResponseEntity with err mesg
	@PutMapping("/change_password")
	public ResponseEntity<?> changePassword(@RequestBody ChangeUserPasswordDto changePasswordDto) {
		System.out.println(changePasswordDto.toString());
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return ResponseEntity.ok(userService.changePassword(changePasswordDto, authentication.getName()));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// Desc - add user address
	// URL - http://host:port/users/add_address
	// Method - POST
	// Query Parameter - email
	// Payload - user address dto
	// Success resp - ResponseEntity with success message
	// err - ResponseEntity with err mesg
	@PostMapping("/add_address")
	public ResponseEntity<?> addUserAddress(@RequestBody UserAddressDto addressDto) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(addressService.addUserAddress(authentication.getName(), addressDto));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// Desc - Get all Addresses of user
	// URL - http://host:port/users/addresses/{email}
	// Method - GET
	// Header -
	// URL Query Parameter -
	// Payload - none
	// Success resp - ResponseEntity with List of Address
	// err - ResponseEntity with err mesg
	@GetMapping("/addresses")
	public ResponseEntity<?> getUserAddresses() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return ResponseEntity.ok(addressService.geUserAddresses(authentication.getName()));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Desc - get user address
	// URL - http://host:port/users/address_details/{addressId}
	// Method - GET
	// Header - addressId
	// URL Query Parameter - none
	// Payload - none
	// Success resp - ResponseEntity with AddressResponseDto
	// err - ResponseEntity with err mesg
	@GetMapping("/address_details/{addressId}")
	public ResponseEntity<?> getUserAddress(@PathVariable Integer addressId) {
		try {
			return ResponseEntity.ok(addressService.getUserAddress(addressId));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	// Desc - update user address
	// URL - http://host:port/users/update_address
	// Method - PUT
	// Header -
	// URL Query Parameter -
	// Payload - AddressResponseDto
	// Success resp - ResponseEntity with succ mesg
	// err - ResponseEntity with err mesg
	@PutMapping("/update_address")
	public ResponseEntity<?> updateUserAddress(@RequestBody AddressResponseDto updatedAddressDto) {
		try {
			return ResponseEntity.ok(addressService.updateUserAddress(updatedAddressDto));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// Desc - delete user address
	// URL - http://host:port/users/delete_address/{addressId}
	// Method - DELETE
	// Header - addressId
	// URL Query Parameter - none
	// Payload - none
	// Success resp - ResponseEntity with ApiResponse succ mesg
	// err - ResponseEntity with err mesg
	@DeleteMapping("/delete_address/{addressId}")
	public ResponseEntity<?> deleteUserAddress(@PathVariable Integer addressId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(addressService.deleteUserAddress(addressId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	// UserControllerApis 
	}

}
