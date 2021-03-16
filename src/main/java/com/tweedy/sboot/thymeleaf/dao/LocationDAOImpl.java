package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.Location;

@Repository
public class LocationDAOImpl implements LocationDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public LocationDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	public List<Location> getLocations() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// create a query  
		Query<Location> theQuery = 
				currentSession.createQuery("from Location",Location.class);
						
		// execute query and get result list
		List<Location> locations = theQuery.getResultList();
								
		// return the results		
		return locations;
	}

	@Override
	public void saveLocation(Location theLocation) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// before saving we have to check if this
		// location is default location and delete 
		// previous default location
		if (theLocation.getDefaultLocation()==1) {		
			String qryString = "update Location set default_location=0";
	        Query query = currentSession.createQuery(qryString);
	        int count = query.executeUpdate();
		}
		
		// save/update the employee ... finally LOL
		currentSession.saveOrUpdate(theLocation);
	}

	@Override
	public Location getLocation(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
						
		// now retrieve/read from database using the primary key
		Location theLocation = currentSession.get(Location.class, theId);
						
		return theLocation;
	}

	@Override
	public void deleteLocation(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// delete object with primary key
		Query theQuery = 
			currentSession.createQuery("delete from Location where locationId=:locationId");
						theQuery.setParameter("locationId", theId);
						
		theQuery.executeUpdate();	

	}


	@Override
	public Location getDefaultLocation() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
						
		// create a query  
		Query<Location> theQuery = 
			currentSession.createQuery("from Location where defaultLocation = 1",Location.class);
								
		// execute query and get result list
		Location defaultLocation = theQuery.getSingleResult();
										
		// return the results		
		return defaultLocation;
	}

}
