package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tweedy.sboot.thymeleaf.dao.CalendarDAO;
import com.tweedy.sboot.thymeleaf.entity.Week;
@Service
public class CalendarServiceImpl implements CalendarService {

	// need to inject address dao
	@Autowired
	private CalendarDAO calendarDAO;
	
	@Autowired
	public CalendarServiceImpl(@Qualifier("calendarDAOImpl") CalendarDAO theCalendarDAO) {
		calendarDAO = theCalendarDAO;
	}
	
	@Override
	@Transactional
	public Boolean hasAttendance(int year, int month, int day) {
		
		Integer result = calendarDAO.getAttendanceIndicator(year, month, day);
		
		if (result==1)
			return true;
		else
			return false;
	}

	@Override
	public String getMonthName(int monthNum) {
		
		return calendarDAO.getMonthName(monthNum);
	}

	@Override
	@Transactional
	public List<Week> getWeeks(int year, int month) {
		
		return calendarDAO.getWeekDays(year, month);
	}
	
	
	
}
