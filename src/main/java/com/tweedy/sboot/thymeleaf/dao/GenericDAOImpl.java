package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.InterfaceGetEmployee;
import com.tweedy.sboot.thymeleaf.entity.Note;
import com.tweedy.sboot.thymeleaf.entity.Phone;
@Repository
public class GenericDAOImpl implements GenericDAO {

	@Value("${package.entity}")
	String packageEntityPath;
	
	// define field for entity manager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public GenericDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public  List<?> getEntities(int theId, String entityName) throws Exception {
		
		Query<?> theQuery;
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		Class c = Class.forName(packageEntityPath + entityName);									
		// create a query  
		try {
			theQuery = 
					currentSession.createQuery("from " + entityName + " where empl_id=:employeeId", 
							c);
		} catch(Exception ex) {
			throw ex;
		}
		
		theQuery.setParameter("employeeId", theId);									
				
		// execute query and get result list
		List<?> entities = theQuery.getResultList();
															
		// return the results		
		return entities;
	}

	@Override
	public <T> void save(T t) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// save/update the phone ... finally LOL
		currentSession.saveOrUpdate(t);	
		
	}

	@Override
	public void delete(int theId, String entityName, String whereClauseColumn) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
										
		// delete object with primary key
		Query theQuery = 
		currentSession.createQuery("delete from "+ entityName 
				+ " where " + whereClauseColumn + "=:" + whereClauseColumn);
		theQuery.setParameter(whereClauseColumn, theId);
												
		theQuery.executeUpdate();	
		
	}

	@Override
	public int findEmployeeByEntityId(int theId, String entityName, String whereClauseColumn) 
			throws Exception {
		int employeeId = -1;
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		Class c = Class.forName(packageEntityPath + entityName);										
		// create a query  
		@SuppressWarnings("unchecked")
		Query<Phone> theQuery = 
			currentSession.createQuery("from " +entityName+ 
									" where "+ whereClauseColumn+ " =:" + whereClauseColumn, c);
			theQuery.setParameter(whereClauseColumn, theId);									
		// execute query and get result list
		List<?> entities = theQuery.getResultList();
				
						 
		for(Object o: entities) {
			InterfaceGetEmployee empl = (InterfaceGetEmployee) o;
			employeeId = empl.extractEmployeeId();
		}
						
		// return the results		
		return employeeId;
	}
	
	

}
