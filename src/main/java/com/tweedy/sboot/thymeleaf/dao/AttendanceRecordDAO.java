package com.tweedy.sboot.thymeleaf.dao;

import java.sql.Date;
import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.AttendanceRecord;

public interface AttendanceRecordDAO {
	public List<AttendanceRecord> getAttendanceRecords(int year, int month, int day);
	
	public List<AttendanceRecord> getAttendanceRecords();
	
	public void saveAttendancerecord(AttendanceRecord theRecord);
	
	public void delete(int theId);
	
	// delete attendance records for the date
	public void deleteAll(int theYear, int theMonth, int theDay);
}
