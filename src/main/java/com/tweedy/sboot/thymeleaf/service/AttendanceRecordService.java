package com.tweedy.sboot.thymeleaf.service;


import java.sql.Date;
import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.AttendanceRecord;

public interface AttendanceRecordService {
	// find all attendance records for a date
	public List<AttendanceRecord> findAll(int year, int month, int day);
		
	// find all attendance records
	public List<AttendanceRecord> findAll();
	
	// save changes made to attendance record
	public void save(AttendanceRecord theRecord);
		
	// delete an attendance record
	public void delete(int recordId);
	
	// delete all attendance records for a single day
	public void deleteAll(int theYear, int theMonth, int theDay);

	
}
