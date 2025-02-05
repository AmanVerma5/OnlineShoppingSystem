package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.address.UserAddressDto;
import com.ecom.services.IAddressService;

@RestController
@RequestMapping("/users/addresses")
public class AddressController {

	@Autowired
	private IAddressService addressService;

	@PostMapping("/{userEmail/add_address}")
	public ResponseEntity<?> addUserAddress(@PathVariable String email, @RequestBody UserAddressDto addressDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addUserAddress(email, addressDto));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
