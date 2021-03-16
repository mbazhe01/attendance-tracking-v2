package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tweedy.sboot.thymeleaf.dao.PhoneDAO;
import com.tweedy.sboot.thymeleaf.dao.PhoneTypeRepository;
import com.tweedy.sboot.thymeleaf.entity.Phone;
import com.tweedy.sboot.thymeleaf.entity.PhoneType;

@Service
public class PhoneServiceImpl implements PhoneService {

	// need to inject phone dao
	@Autowired
	private PhoneDAO phoneDAO;
	
	@Autowired
	PhoneTypeRepository phoneTypeRepo;
	
	@Autowired
	public PhoneServiceImpl(@Qualifier("phoneDAOImpl") PhoneDAO thePhoneDAO) {
		phoneDAO = thePhoneDAO;
	}
	
	
	@Override
	@Transactional
	public List<Phone> findAll(int employeeId) {
		return phoneDAO.getPhones(employeeId);
	}

	@Override
	@Transactional
	public void save(Phone thePhone) {
		phoneDAO.save(thePhone);

	}

	@Override
	@Transactional
	public void deletePhone(int phoneId) {
		phoneDAO.delete(phoneId);

	}

	@Override
	@Transactional
	public int getEmployeeByPhoneId(int phoneId) {
		// TODO Auto-generated method stub
		return phoneDAO.findEmployeeByPhoneId(phoneId);
	}


	@Override
	public List<PhoneType> getPhoneTypes() {
		
		return phoneTypeRepo.findAll();
	}

}
