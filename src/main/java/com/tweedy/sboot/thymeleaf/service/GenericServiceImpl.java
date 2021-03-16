package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tweedy.sboot.thymeleaf.dao.GenericDAO;
import com.tweedy.sboot.thymeleaf.dao.PhoneDAO;

@Service
public class GenericServiceImpl implements GenericService {
		
	// need to inject dao
	@Autowired
	private GenericDAO dao;

	@Autowired
	public GenericServiceImpl(@Qualifier("genericDAOImpl") GenericDAO theDAO) {
		dao = theDAO;
	}
	
	@Override
	@Transactional
	public List<?> findAll(int theId, String entityName) throws Exception {
		
		return dao.getEntities( theId, entityName);
	}

	@Override
	@Transactional
	public <T> void save(T t) {
		dao.save(t);

	}
	
	@Override
	@Transactional
	public int getEmployeeByEntityId(int theId, String entityName, String whereClauseColumn) throws Exception {
		// TODO Auto-generated method stub
		return dao.findEmployeeByEntityId(theId, entityName, whereClauseColumn);
	}

	@Override
	@Transactional
	public void delete(int theId, String entityName, String whereClauseColumn) {
		dao.delete(theId, entityName, whereClauseColumn);
		
	}

}
