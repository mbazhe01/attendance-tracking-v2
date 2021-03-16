package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

/**
 * the wrapper class has been created
 * to pass a list to Thymeleaf template
 * @author mikeba
 *
 */
public class AddressesWrapper {
	List<Address> addresses;

	// default constructor
	public AddressesWrapper() {}

	// Parameterized constructor
	public AddressesWrapper(List<Address> addresses) {	
		this.addresses = addresses;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(Address address) {
        this.addresses.add(address);
    }
	
	public int getEmployeeId()  {
		int emplId = -1;
		
		if (addresses != null && addresses.size() > 0) {
			
			for(Address a : addresses) {
				emplId = a.getEmployee().getEmplId();
			}
			
		}
		
		return emplId;
		
	}

}
