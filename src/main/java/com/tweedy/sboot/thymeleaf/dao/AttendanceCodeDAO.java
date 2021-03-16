package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.AttendanceCode;


public interface AttendanceCodeDAO {

	public List<AttendanceCode> getAttendanceCodes();

	public void saveAttendanceCode(AttendanceCode theAttendanceCode);

	public AttendanceCode getAttendanceCode(int theId);

	public void deleteAttendanceCode(int theId);
}
