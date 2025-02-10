package com.ecom.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.user.ChangeUserPasswordDto;
import com.ecom.dtos.user.RegisterDto;
import com.ecom.dtos.user.UserDetailsDto;
import com.ecom.entities.User;
import com.ecom.exceptions.ApiException;
import com.ecom.respositories.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ApiResponse registerUser(RegisterDto registerUserDetails) throws ApiException {

		if (userRepository.existsByEmail(registerUserDetails.getEmail())) {
			throw new ApiException("User with this email already exists");
		}
		registerUserDetails.setPassword(passwordEncoder.encode(registerUserDetails.getPassword()));
		User persistentUser = userRepository.save(modelMapper.map(registerUserDetails, User.class));
		persistentUser.setActive(true);
		return new ApiResponse("User Register Successfully");
	}

//	@Override
//	public AuthResponseDto signInUser(AuthRequestDto loginDto) throws ResourceNotFoundException {
//		User persistentUser = userRepository.getUserByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
//		if (persistentUser != null) {
//			return modelMapper.map(persistentUser, AuthResponseDto.class);
//		} else {
//			throw new ResourceNotFoundException("Invalid Credentials");
//		}
//	}

	@Override
	public UserDetailsDto getUserDetails(String email) {
		return modelMapper.map(userRepository.getUserByEmail(email), UserDetailsDto.class);
	}

	@Override
	public ApiResponse updateUser(UserDetailsDto updateUserDetails, String email) {

		User updateUser = userRepository.getUserByEmail(email);
		updateUser.setFirstName(updateUserDetails.getFirstName());
		updateUser.setLastName(updateUserDetails.getLastName());
		updateUser.setMobileNo(updateUserDetails.getMobileNo());
		updateUser.setDateOfBirth(updateUserDetails.getDateOfBirth());

		return new ApiResponse("Details Updated");
	}

	@Override
	public ApiResponse changePassword(ChangeUserPasswordDto changePasswordDto, String email) {

		User persistentUser = userRepository.getUserByEmail(email);
		if (persistentUser != null) {
			boolean isMatch = passwordEncoder.matches(changePasswordDto.getCurrentPassword(),
					persistentUser.getPassword());
			if (isMatch) {
				persistentUser.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
				return new ApiResponse("Password updated successfully");
			} else {
				throw new ApiException("Invalid current password");
			}
		} else {
			throw new ApiException("Invalid Email");
		}

	}

}
