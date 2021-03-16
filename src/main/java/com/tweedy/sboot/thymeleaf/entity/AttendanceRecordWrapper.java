package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

public class AttendanceRecordWrapper {
	List<AttendanceRecord> records;
	
	public AttendanceRecordWrapper() {}

	public AttendanceRecordWrapper(List<AttendanceRecord> records) {
		this.records = records;
	}

	public List<AttendanceRecord> getRecords() {
		return records;
	}

	public void setRecords(List<AttendanceRecord> records) {
		this.records = records;
	}
	

}
