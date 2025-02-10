package com.ecom.dtos.user;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {

	private String firstName;

	private String lastName;
	
	private String mobileNo;
	
	private String email;

	private LocalDate dateOfBirth;
	
}
