package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.Phone;
import com.tweedy.sboot.thymeleaf.entity.PhoneType;

public interface PhoneService {
	// find all addresses for an employee
	public List<Phone> findAll(int employeeId);
		
	// save changes made to phone
	public void save(Phone thePhone);
		
	// delete a phone
	public void deletePhone(int phoneId);
		
	// get employee by phone id
	public int getEmployeeByPhoneId (int phoneId);
	
	public List<PhoneType> getPhoneTypes();
}
