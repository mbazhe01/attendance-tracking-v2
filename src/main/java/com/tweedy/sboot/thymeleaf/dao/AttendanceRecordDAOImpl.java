package com.tweedy.sboot.thymeleaf.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.AttendanceRecord;

@Repository
public class AttendanceRecordDAOImpl implements AttendanceRecordDAO {

	// define field for entity manager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public AttendanceRecordDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<AttendanceRecord> getAttendanceRecords(int year, int month, int day) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
											
		// create a query  
		Query<AttendanceRecord> theQuery = 
			currentSession.createQuery("from AttendanceRecord where year(recDate) = :year"
					+ " and month(recDate) = :month"
					+ " and day(recDate) = :day" , 
						AttendanceRecord.class);
		theQuery.setParameter("year", year);
		theQuery.setParameter("month", month);
		theQuery.setParameter("day", day);
				
		// execute query and get result list
		List<AttendanceRecord> records = theQuery.getResultList();
		
		// return the results		
		return records;
	}

	@Override
	public void saveAttendancerecord(AttendanceRecord theRecord) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// save/update the attendance record
		currentSession.saveOrUpdate(theRecord);

	}

	@Override
	public void delete(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
								
		// delete object with primary key
		Query theQuery = 
			currentSession.createQuery("delete from AttendanceRecord where recId=:recordId");
			theQuery.setParameter("recordId", theId);
										
		theQuery.executeUpdate();	

	}

	@Override
	public List<AttendanceRecord> getAttendanceRecords() {
		// get the current hibernate session
				Session currentSession = entityManager.unwrap(Session.class);
													
		// create a query  
		Query<AttendanceRecord> theQuery = 
					currentSession.createQuery("from AttendanceRecord", AttendanceRecord.class);
												
		theQuery.setMaxResults(100);				
		// execute query and get result list
		List<AttendanceRecord> records = theQuery.getResultList();
																	
		// return the results		
		return records;
	}

	@Override
	public void deleteAll(int theYear, int theMonth, int theDay) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
										
		// delete object with primary key
		Query theQuery = 
			currentSession.createQuery("delete from AttendanceRecord where year(recDate) = :year"
					+ " and month(recDate) = :month"
					+ " and day(recDate) = :day");
			theQuery.setParameter("year", theYear);
			theQuery.setParameter("month", theMonth);
			theQuery.setParameter("day", theDay);
		theQuery.executeUpdate();	
		
	}

}// eoc
