package com.tweedy.sboot.thymeleaf.service;


import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tweedy.sboot.thymeleaf.dao.AttendanceRecordDAO;
import com.tweedy.sboot.thymeleaf.entity.AttendanceRecord;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {

	// need to inject address dao
	@Autowired
	private AttendanceRecordDAO recordDAO;
		
	@Autowired
	public AttendanceRecordServiceImpl(@Qualifier("attendanceRecordDAOImpl") AttendanceRecordDAO attendanceRecordDAO) {
		recordDAO = attendanceRecordDAO;
	}
	
	@Override
	@Transactional
	public List<AttendanceRecord> findAll(int year, int month, int day) {
		
		return recordDAO.getAttendanceRecords( year,  month,  day);
	}

	@Override
	@Transactional
	public void save(AttendanceRecord theRecord) {
		// TODO Auto-generated method stub
		recordDAO.saveAttendancerecord(theRecord);
	}

	@Override
	@Transactional
	public void delete(int recordId) {
		// TODO Auto-generated method stub
		recordDAO.delete(recordId);
	}

	@Override
	@Transactional
	public List<AttendanceRecord> findAll() {
		return recordDAO.getAttendanceRecords();
	}

	@Override
	@Transactional
	public void deleteAll(int theYear, int theMonth, int theDay) {
		// TODO Auto-generated method stub
		recordDAO.deleteAll(theYear, theMonth, theDay);
	}
	
}
