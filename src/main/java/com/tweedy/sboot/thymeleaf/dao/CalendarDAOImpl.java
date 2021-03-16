package com.tweedy.sboot.thymeleaf.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Day;
import com.tweedy.sboot.thymeleaf.entity.Week;

@Repository
public class CalendarDAOImpl implements CalendarDAO {
	@Value("${sp.GetAttendanceIndicator}")
	String spGetAttendanceIndicator;
	
	// define field for entity manager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public CalendarDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public int getAttendanceIndicator(int year, int month, int dayOfMonth) {
		Integer result = 0;
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		StoredProcedureQuery query 
			= entityManager.createStoredProcedureQuery(spGetAttendanceIndicator)
				.registerStoredProcedureParameter("year", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("month", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("day", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("indicator", Integer.class, ParameterMode.OUT)
				.setParameter("year", year)
				.setParameter("month", month)
				.setParameter("day", dayOfMonth);
		
		try {
		    query.execute();
		     
		    result = (Integer) query
		      .getOutputParameterValue("indicator");
		 		    
		} finally {
		    query.unwrap(ProcedureOutputs.class)
		    .release();
		}				
				
		// return the results		
		return result;
	}


	@Override
	public String getMonthName(int monthNum) {
		String rtn=null;
	
		switch(monthNum) {
		case 0: 
			rtn = "Jan";
			break;
		case 1: 
			rtn = "Feb";
			break;
		case 2: 
			rtn = "Mar";
			break;
		case 3: 
			rtn = "Apr";
			break;
		case 4: 
			rtn = "May";
			break;
		case 5: 
			rtn = "Jun";
			break;
		case 6: 
			rtn = "Jul";
			break;
		case 7: 
			rtn = "Aug";
			break;
		case 8: 
			rtn = "Sep";
			break;
		case 9: 
			rtn = "Oct";
			break;
		case 10: 
			rtn = "Nov";
			break;
		case 11: 
			rtn = "Dec";
			break;
		default:
			break;
		}
		return rtn;
	}


	@Override
	public List<Week> getWeekDays(int year, int month) {
		List<Week> weeks = new ArrayList<>();
		
		// get number of the days in the month
		Calendar cal = new GregorianCalendar(year, month-1, 1);
		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		// get what day of the week is the first month
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	
		
		// fill a list with days of the month
		List<Day> monthDays = new ArrayList<Day>();
		int daysToFill = dayOfWeek - 1;
					
		for (int i=0; i< daysToFill; i++) {
			monthDays.add(new Day());
		}
				
		for (Integer i= 1; i<= daysInMonth; i++) {
						
			Boolean hasAttendance;
			if (getAttendanceIndicator(year, month, i)==1)
				hasAttendance= true;
			else
				hasAttendance=false;
			
			Day day = new Day(year, month, i, hasAttendance );
			monthDays.add(day);
		}
		
		// split the days list into weeks
		Week week = new Week();
		for (Day d:monthDays) {			
					
			if (week.size()<7)
				week.add(d);
			else {
				weeks.add(week);
				week = new Week();
				week.add(d);
			}				
		}//eof for loop
		weeks.add(week);
		
		return weeks;
	}

}// eoc
