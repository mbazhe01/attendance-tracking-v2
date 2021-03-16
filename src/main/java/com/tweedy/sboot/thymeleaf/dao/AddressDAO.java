package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.Location;

public interface AddressDAO {

	public List<Address> getAddresses(int theId);
	
	public void saveAddress(Address theAddress);
	
	public void delete(int theId);
	
	public int findEmployeeByAddrId(int addrId);
}
