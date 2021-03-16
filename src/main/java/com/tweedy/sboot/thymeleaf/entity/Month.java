package com.tweedy.sboot.thymeleaf.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.time.*;
/***
 * Produces monthly date table
 * @author mikeba
 *
 */
public class Month {
	private Integer year;
	private Integer month;
	private List<Week> weeks;
	
	/**
	 * Constructor
	 * @param year
	 * @param month
	 * @throws Exception 
	 */
	public Month(Integer year, Integer month) throws Exception {
		try {
			this.year = year;
			this.month = month;
			weeks = new ArrayList<Week>();
		} catch (Exception ex) {
			throw new Exception("Month.Constructor: " + ex.getMessage());
		}
		
	}// eof Constructor
	
	/**
	 * Returns number of the weeks in the month
	 * @return
	 */
	public int getNumberOfWeeks() {
		return weeks.size();
	}

	public List<Week> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<Week> weeks) {
		this.weeks = weeks;
	}
	
	
	/**
	 * Returns a list of the days in the first week of the month
	 * @param weekNum - number of the week in month. Could be 1..5
	 * @return
	 * @throws Exception
	 */
	public List<Day> getWeekDays(int weekNum) throws Exception {
		try {
			ArrayList<Day> days = new ArrayList<Day>();
			
			Week w = weeks.get(weekNum-1);
			
			for (Day day: w.getWeekDays()) {
					days.add(day);
			}		
			
			return days;
		}
		catch(Exception ex) {
			throw new Exception ("Month.getWeek1Days : " + ex.getMessage());
		}
	}//eof getWeek1Days()
	

}
