package com.ecom.services;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.user.AuthRequestDto;
import com.ecom.dtos.user.AuthResponseDto;
import com.ecom.dtos.user.RegisterDto;

public interface IUserService {

	ApiResponse registerUser(RegisterDto registerUserDetails);

	AuthResponseDto signInUser(AuthRequestDto loginDto);

}
