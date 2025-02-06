package com.ecom.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.address.AddressResponseDto;
import com.ecom.dtos.address.UserAddressDto;
import com.ecom.entities.Address;
import com.ecom.entities.User;
import com.ecom.exceptions.ApiException;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.respositories.AddressRepository;
import com.ecom.respositories.UserRepository;

@Service
@Transactional
public class AddressService implements IAddressService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse addUserAddress(String email, UserAddressDto addressDto) {
		User persistentUser = userRepository.getUserByEmail(email);
		if (persistentUser.getAddresses().size() >= 3) {
			throw new ApiException("User can have a maximum of 3 addresses");
		}
		Address address = modelMapper.map(addressDto, Address.class);
		persistentUser.getAddresses().add(address);
		address.setUser(persistentUser);
		return new ApiResponse("Address added successfully");
	}

	@Override
	public List<AddressResponseDto> geUserAddresses(String email) {
		User persistentUser = userRepository.getUserByEmail(email);
		List<Address> userAddresses = persistentUser.getAddresses();
		if (userAddresses == null) {
			throw new ResourceNotFoundException("No address records were found");
		}
		return userAddresses.stream().map(userAddress -> modelMapper.map(userAddress, AddressResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public AddressResponseDto getUserAddress(Integer addressId) {
		Address userAddress = addressRepository.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Address Id"));
		return modelMapper.map(userAddress, AddressResponseDto.class);
	}

	@Override
	public ApiResponse updateUserAddress(AddressResponseDto updatedAddressDto) {
		Address persistentAddress = addressRepository.findById(updatedAddressDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Address Id"));

		persistentAddress.setAddressLine1(updatedAddressDto.getAddressLine1());
		persistentAddress.setAddressLine2(updatedAddressDto.getAddressLine2());
		persistentAddress.setCity(updatedAddressDto.getCity());
		persistentAddress.setState(updatedAddressDto.getState());
		persistentAddress.setCountry(updatedAddressDto.getCountry());
		persistentAddress.setPincode(updatedAddressDto.getPincode());

		return new ApiResponse("Address updated");
	}

}
