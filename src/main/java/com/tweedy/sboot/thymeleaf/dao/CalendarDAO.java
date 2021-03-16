package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.Day;
import com.tweedy.sboot.thymeleaf.entity.Week;

public interface CalendarDAO {
	int getAttendanceIndicator(int year, int month, int dayOfMonth);
	
	String getMonthName(int monthNum);
	
	List<Week> getWeekDays(int year, int month);
}
