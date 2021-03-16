package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Department;
import com.tweedy.sboot.thymeleaf.entity.Location;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
			
	// set up constructor injection
	@Autowired
	public DepartmentDAOImpl(EntityManager theEntityManager) {
			entityManager = theEntityManager;
	}
	
	
	@Override
	public List<Department> getDepartments() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
						
		// create a query  
		Query<Department> theQuery = 
				currentSession.createQuery("from Department", Department.class);
								
		// execute query and get result list
		List<Department> departments = theQuery.getResultList();
										
		// return the results		
		return departments;
	}

	@Override
	public void saveDepartment(Department theDepartment) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
								
		// save/update the employee ... finally LOL
		currentSession.saveOrUpdate(theDepartment);
		
	}

	@Override
	public Department getDepartment(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
								
		// now retrieve/read from database using the primary key
		Department theDepartment = currentSession.get(Department.class, theId);
								
		return theDepartment;
	}

	@Override
	public void deleteDepartment(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
						
		// delete object with primary key
		Query theQuery = 
					currentSession.createQuery("delete from Department where departmentId=:departmentId");
		theQuery.setParameter("departmentId", theId);
					
		theQuery.executeUpdate();	

	}

}
