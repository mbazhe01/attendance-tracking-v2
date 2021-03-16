package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

public class AttendanceYearsWrap {

	private List<AttendanceYear> attendanceYears;

	public AttendanceYearsWrap(List<AttendanceYear> attendanceYears) {
		this.attendanceYears = attendanceYears;
	}

	public List<AttendanceYear> getAttendanceYears() {
		return attendanceYears;
	}

	public void setAttendanceYears(List<AttendanceYear> attendanceYears) {
		this.attendanceYears = attendanceYears;
	}
	
	
	
}
