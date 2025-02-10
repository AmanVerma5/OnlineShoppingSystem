package com.ecom.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.user.AuthRequestDto;
import com.ecom.dtos.user.AuthResponseDto;
import com.ecom.dtos.user.ChangeUserPasswordDto;
import com.ecom.dtos.user.RegisterDto;
import com.ecom.dtos.user.UpdateUserDto;
import com.ecom.dtos.user.UserDetailsDto;
import com.ecom.entities.User;
import com.ecom.exceptions.ApiException;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.respositories.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse registerUser(RegisterDto registerUserDetails) throws ApiException {

		if (userRepository.existsByEmail(registerUserDetails.getEmail())) {
			throw new ApiException("User with this email already exists");
		}
		User persistentUser = modelMapper.map(registerUserDetails, User.class);
		persistentUser.setActive(true);
		userRepository.save(persistentUser);
		return new ApiResponse("User Register Successfully");
	}

	@Override
	public AuthResponseDto signInUser(AuthRequestDto loginDto) throws ResourceNotFoundException {
		User persistentUser = userRepository.getUserByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
		if (persistentUser != null) {
			return modelMapper.map(persistentUser, AuthResponseDto.class);
		} else {
			throw new ResourceNotFoundException("Invalid Credentials");
		}
	}

	@Override
	public ApiResponse updateUser(UpdateUserDto updateUserDetails, String email) {

		User updateUser = userRepository.getUserByEmail(email);
		updateUser.setFirstName(updateUserDetails.getFirstName());
		updateUser.setLastName(updateUserDetails.getLastName());
		updateUser.setMobileNo(updateUserDetails.getMobileNo());
		updateUser.setDateOfBirth(updateUserDetails.getDateOfBirth());

		return new ApiResponse("Details Updated");
	}

	@Override
	public ApiResponse updateUser(UserDetailsDto updateUserDetails, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse changePassword(ChangeUserPasswordDto changePasswordDto, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetailsDto getUserDetails(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
