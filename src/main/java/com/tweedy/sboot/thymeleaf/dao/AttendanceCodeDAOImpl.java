package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.AttendanceCode;
import com.tweedy.sboot.thymeleaf.entity.Department;

@Repository
public class AttendanceCodeDAOImpl implements AttendanceCodeDAO {
	// define field for entitymanager	
	private EntityManager entityManager;
				
	// set up constructor injection
	@Autowired
	public AttendanceCodeDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
		
	@Override
	public List<AttendanceCode> getAttendanceCodes() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
								
		// create a query  
		Query<AttendanceCode> theQuery = 
				currentSession.createQuery("from AttendanceCode order by attendanceCode", AttendanceCode.class);
										
		// execute query and get result list
		List<AttendanceCode> attendanceCodes = theQuery.getResultList();
												
		// return the results		
		return attendanceCodes;
	}

	@Override
	public void saveAttendanceCode(AttendanceCode theAttendanceCode) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
										
		// save/update the attendance code ... finally LOL
		currentSession.saveOrUpdate(theAttendanceCode);
	}

	@Override
	public AttendanceCode getAttendanceCode(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
										
		// now retrieve/read from database using the primary key
		AttendanceCode theAttendanceCode = currentSession.get(AttendanceCode.class, theId);
										
		return theAttendanceCode;
	}

	@Override
	public void deleteAttendanceCode(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
								
		// delete object with primary key
		Query theQuery = 
					currentSession.createQuery("delete from AttendanceCode where attendanceCodeId=:attendanceCodeId");
		theQuery.setParameter("attendanceCodeId", theId);
							
		theQuery.executeUpdate();	

	}

}
