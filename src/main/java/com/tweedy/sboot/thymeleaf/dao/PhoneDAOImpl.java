package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.Phone;

@Repository
public class PhoneDAOImpl implements PhoneDAO {

	// define field for entity manager	
	private EntityManager entityManager;
	
	// set up constructor injection
	@Autowired
	public PhoneDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	public List<Phone> getPhones(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
											
		// create a query  
		Query<Phone> theQuery = 
			currentSession.createQuery("from Phone where empl_id=:employeeId", Phone.class);
			theQuery.setParameter("employeeId", theId);									
				
		// execute query and get result list
		List<Phone> phones = theQuery.getResultList();
															
		// return the results		
		return phones;
	}

	@Override
	public void save(Phone thePhone) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// save/update the phone ... finally LOL
		currentSession.saveOrUpdate(thePhone);	
	}

	@Override
	public void delete(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
								
		// delete object with primary key
		Query theQuery = 
		currentSession.createQuery("delete from Phone where phoneId=:phoneId");
										theQuery.setParameter("phoneId", theId);
										
		theQuery.executeUpdate();	
				

	}

	@Override
	public int findEmployeeByPhoneId(int phoneId) {
		int employeeId = -1;
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
											
		// create a query  
		Query<Phone> theQuery = 
			currentSession.createQuery("from Phone where phone_id=:phoneId", Phone.class);
				theQuery.setParameter("phoneId", phoneId);									
				
		// execute query and get result list
		List<Phone> phones = theQuery.getResultList();
		
		for(Phone p: phones)
			employeeId = p.getEmployee().getEmplId();
						
		// return the results		
		return employeeId;
	}

}
