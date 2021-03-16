package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.AddressLabel;
import com.tweedy.sboot.thymeleaf.entity.Department;

@Repository
public class AddressLabelDAOImpl implements AddressLabelDAO {

	// define field for entitymanager	
		private EntityManager entityManager;
				
	
	// set up constructor injection
	@Autowired
	public AddressLabelDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}	
		
	@Override
	public List<AddressLabel> getAddressLabels() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
								
		// create a query  
		Query<AddressLabel> theQuery = 
				currentSession.createQuery("from AddressLabel", AddressLabel.class);
										
		// execute query and get result list
		List<AddressLabel> labels = theQuery.getResultList();
												
		// return the results		
		return labels;
	}

}
