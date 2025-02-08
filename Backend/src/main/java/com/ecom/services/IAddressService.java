package com.ecom.services;

import java.util.List;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.address.AddressResponseDto;
import com.ecom.dtos.address.UserAddressDto;

public interface IAddressService {

	ApiResponse addUserAddress(String email, UserAddressDto addressDto);

	List<AddressResponseDto> geUserAddresses(String email);

	AddressResponseDto getUserAddress(Integer addressId);

	ApiResponse updateUserAddress(AddressResponseDto updatedAddressDto);

	ApiResponse deleteUserAddress(Integer addressId);

}
