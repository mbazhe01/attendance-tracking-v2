package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

public class Attendance {
	
	private List<Integer> attendanceYears;

	public Attendance() {
		
	}

	public List<Integer> getAttendanceYears() {
		return attendanceYears;
	}

	public void setAttendanceYears(List<Integer> attendanceYears) {
		this.attendanceYears = attendanceYears;
	}
	
	
	
}
