package com.tweedy.sboot.thymeleaf.entity;

import java.text.DateFormatSymbols;

/***
 * This class represents info for one calendar day
 * @author mikeba
 *
 */
public class Day {
	private int year;
	private int month;
	private Integer dayOfMonth;
	Boolean hasAttendance;		// true - attendance exists for the day, false - no attendance
	private String monthName;
	

	/**
	 * Constructor
	 * @param year
	 * @param month
	 * @param number
	 */
	public Day(int year, int month, int dayOfMonth) throws Exception {
		
		try {
			this.setYear(year);
			this.setMonth(month);
			this.setDayOfMonth(dayOfMonth);
			
			monthName = new DateFormatSymbols().getMonths()[month-1];
			
			//attendIndicator = dbUtil.getAttendanceIndicator(this.year, this.month, this.dayOfMonth);
		} catch(Exception ex) {
			throw new Exception ("Day.Constructor : " + ex.getMessage());
		}
		
	}
	
	public Day(int year, int month, Integer dayOfMonth, Boolean hasAttendance) {
		
		this.year = year;
		this.month = month;
		this.dayOfMonth = dayOfMonth;
		this.hasAttendance = hasAttendance;
	}

	public Day () {
		this.dayOfMonth = 0;
		this.hasAttendance = false;
	}
	
	public void setNull() {
		this.dayOfMonth = null;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public Boolean getHasAttendance() {
		return hasAttendance;
	}

	public void setHasAttendance(Boolean hasAttendance) {
		this.hasAttendance = hasAttendance;
	}
	
	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
						
		this.monthName = monthName;
	}
	
	
}// eoc
