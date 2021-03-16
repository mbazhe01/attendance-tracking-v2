package com.tweedy.sboot.thymeleaf.entity;

public class AttendanceYear {
	private int year;

	public AttendanceYear(int year) {
		this.year = year;
	}
	
	public AttendanceYear() {}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
