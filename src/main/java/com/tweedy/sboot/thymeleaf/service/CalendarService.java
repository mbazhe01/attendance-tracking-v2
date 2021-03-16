package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.Week;

public interface CalendarService {
	public Boolean hasAttendance(int year, int month, int day );
	public String getMonthName(int monthNum);
	public List<Week> getWeeks(int year, int month);
	
}
