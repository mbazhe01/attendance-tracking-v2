package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tweedy.sboot.thymeleaf.dao.AddressDAO;
import com.tweedy.sboot.thymeleaf.dao.AddressTypeRepository;
import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.AddressType;

@Service
public class AddressServiceImpl implements AddressService {

	// need to inject address dao
	@Autowired
	private AddressDAO addressDAO;
	
	@Autowired
	private AddressTypeRepository addrTypeRepo;
	
	@Autowired
	public AddressServiceImpl(@Qualifier("addressDAOImpl") AddressDAO theAddressDAO) {
		addressDAO = theAddressDAO;
	}
	
	@Override
	@Transactional
	public List<Address> findAll(int employeeId) {
		return addressDAO.getAddresses(employeeId);
	}

	@Override
	@Transactional
	public void save(Address theAddress) {
		addressDAO.saveAddress(theAddress);
		
	}

	@Override
	@Transactional
	public void deleteAddress(int addrId) {
		
		addressDAO.delete(addrId);
		
	}

	@Override
	@Transactional
	public int getEmployeeByAddrId(int addrId) {
		
		return addressDAO.findEmployeeByAddrId(addrId);
	}

	@Override
	public List<AddressType> getAddressTypes() {
		
		return addrTypeRepo.findAll();
	}
	
	

}//eoc
