package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.Phone;

public interface PhoneDAO {
	public List<Phone> getPhones(int theId);
	
	public void save(Phone thePhone);
	
	public void delete(int theId);
	
	public int findEmployeeByPhoneId(int phoneId);
}
