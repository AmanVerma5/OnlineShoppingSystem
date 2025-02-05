package com.ecom.services;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.address.UserAddressDto;

public interface IAddressService {

	ApiResponse addUserAddress(String email, UserAddressDto addressDto);

}
