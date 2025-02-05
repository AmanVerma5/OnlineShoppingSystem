package com.ecom.services;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.user.AuthRequestDto;
import com.ecom.dtos.user.AuthResponseDto;
import com.ecom.dtos.user.ChangeUserPasswordDto;
import com.ecom.dtos.user.RegisterDto;
import com.ecom.dtos.user.UpdateUserDto;

public interface IUserService {

	ApiResponse registerUser(RegisterDto registerUserDetails);

	AuthResponseDto signInUser(AuthRequestDto loginDto);

	ApiResponse updateUser(UpdateUserDto updateUserDetails, String email);

	ApiResponse changePassword(ChangeUserPasswordDto changePasswordDto, String email);

}
