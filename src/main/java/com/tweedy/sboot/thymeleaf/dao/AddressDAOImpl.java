package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.Employee;

@Repository
public class AddressDAOImpl implements AddressDAO {

	// define field for entity manager	
	private EntityManager entityManager;
	
	// set up constructor injection
	@Autowired
	public AddressDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Address> getAddresses(int theId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
									
		// create a query  
		Query<Address> theQuery = 
			currentSession.createQuery("from Address where empl_id=:employeeId", Address.class);
		theQuery.setParameter("employeeId", theId);									
		
		// execute query and get result list
		List<Address> addresses = theQuery.getResultList();
													
		// return the results		
		return addresses;
	}

	@Override
	public void saveAddress(Address theAddress) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// save/update the address ... finally LOL
		currentSession.saveOrUpdate(theAddress);	
	}

	@Override
	public void delete(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
						
		// delete object with primary key
		Query theQuery = 
			currentSession.createQuery("delete from Address where addrId=:addressId");
								theQuery.setParameter("addressId", theId);
								
		theQuery.executeUpdate();	
		
	}

	@Override
	public int findEmployeeByAddrId(int addrId) {
		int employeeId = -1;
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
											
		// create a query  
		Query<Address> theQuery = 
			currentSession.createQuery("from Address where addr_id=:addressId", Address.class);
				theQuery.setParameter("addressId", addrId);									
				
		// execute query and get result list
		List<Address> addresses = theQuery.getResultList();
		
		for(Address a: addresses)
			employeeId = a.getEmployee().getEmplId();
		
		// return the results		
		return employeeId;
	}


}
