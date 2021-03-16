package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tweedy.sboot.thymeleaf.dao.AddressLabelDAO;
import com.tweedy.sboot.thymeleaf.dao.DepartmentDAO;
import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.AddressLabel;

@Service
public class AddressLabelServiceImpl implements AddressLabelService {

	// need to inject location dao
	@Autowired
	private AddressLabelDAO addressLabelDAO;
				
	@Autowired
	public AddressLabelServiceImpl(@Qualifier("addressLabelDAOImpl") AddressLabelDAO theAddressLabelDAO) {
		addressLabelDAO = theAddressLabelDAO;
	}
	
	
	@Override
	@Transactional
	public List<AddressLabel> findAll() {
		return addressLabelDAO.getAddressLabels();
	}

}
