package com.ecom.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.address.UserAddressDto;
import com.ecom.entities.Address;
import com.ecom.entities.User;
import com.ecom.exceptions.ApiException;
import com.ecom.respositories.UserRepository;

@Service
@Transactional
public class AddressService implements IAddressService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ApiResponse addUserAddress(String email, UserAddressDto addressDto) {
		User persistentUser = userRepository.getUserByEmail(email);
		if (persistentUser.getAddresses().size() >= 3) {
			throw new ApiException("User can have a maximum of 3 addresses");
		}
		persistentUser.getAddresses().add(modelMapper.map(addressDto, Address.class));
		return null;
	}

}
