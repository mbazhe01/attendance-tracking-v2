package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tweedy.sboot.thymeleaf.dao.AttendanceCodeDAO;
import com.tweedy.sboot.thymeleaf.dao.DepartmentDAO;
import com.tweedy.sboot.thymeleaf.entity.AttendanceCode;

@Service
public class AttendanceCodeServiceImpl implements AttendanceCodeService {

	// need to inject location dao
	@Autowired
	private AttendanceCodeDAO attendanceCodeDAO;
				
	@Autowired
	public AttendanceCodeServiceImpl(@Qualifier("attendanceCodeDAOImpl") AttendanceCodeDAO theAttendanceCodeDAO) {
		attendanceCodeDAO = theAttendanceCodeDAO;
	}
	
	@Override
	@Transactional
	public List<AttendanceCode> findAll() {
		return attendanceCodeDAO.getAttendanceCodes();
	}

	@Override
	@Transactional
	public AttendanceCode findById(int theId) {
		return attendanceCodeDAO.getAttendanceCode(theId);
	}

	@Override
	@Transactional
	public void save(AttendanceCode theAttendanceCode) {
		attendanceCodeDAO.saveAttendanceCode(theAttendanceCode);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
	
		attendanceCodeDAO.deleteAttendanceCode(theId);
	}

}
