package com.ecom.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
