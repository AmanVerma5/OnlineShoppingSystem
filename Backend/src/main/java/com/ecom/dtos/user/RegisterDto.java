package com.ecom.dtos.user;

import java.time.LocalDate;

import com.ecom.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

	private String firstName;

	private String lastName;

	private String email;

	private String mobileNo;

	private LocalDate dateOfBirth;

	private String password;
	
	private UserRole userRole;
	
}
