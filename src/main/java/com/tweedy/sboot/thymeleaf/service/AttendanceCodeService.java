package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.AttendanceCode;
import com.tweedy.sboot.thymeleaf.entity.Department;

public interface AttendanceCodeService {
	public List<AttendanceCode> findAll();
	
	public AttendanceCode findById(int theId);
	
	public void save(AttendanceCode theAttendanceCode);
	
	public void deleteById(int theId);
}
