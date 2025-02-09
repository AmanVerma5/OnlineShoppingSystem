package com.ecom.services;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.user.ChangeUserPasswordDto;
import com.ecom.dtos.user.RegisterDto;
import com.ecom.dtos.user.UserDetailsDto;

public interface IUserService {

	ApiResponse registerUser(RegisterDto registerUserDetails);

//	AuthResponseDto signInUser(AuthRequestDto loginDto);

	ApiResponse updateUser(UserDetailsDto updateUserDetails, String email);

	ApiResponse changePassword(ChangeUserPasswordDto changePasswordDto, String email);

	UserDetailsDto getUserDetails(String email);

}
