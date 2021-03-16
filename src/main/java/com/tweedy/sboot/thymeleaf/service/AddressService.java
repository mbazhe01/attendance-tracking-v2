package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.AddressType;

public interface AddressService {
	
	// find all addresses for an employee
	public List<Address> findAll(int employeeId);
	
	// save changes made to address
	public void save(Address theAddress);
	
	// delete an address
	public void deleteAddress(int addrId);
	
	// get employee by address id
	public int getEmployeeByAddrId (int addrId);
	
	public List<AddressType> getAddressTypes();

}
