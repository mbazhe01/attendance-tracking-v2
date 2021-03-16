package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tweedy.sboot.thymeleaf.dao.AddressDAO;
import com.tweedy.sboot.thymeleaf.dao.AttendanceDAO;
import com.tweedy.sboot.thymeleaf.entity.AttendanceMonthly;
import com.tweedy.sboot.thymeleaf.entity.CarryOver;
import com.tweedy.sboot.thymeleaf.entity.LocationMonthly;
import com.tweedy.sboot.thymeleaf.entity.VacationTotals;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	// need to inject address dao
	@Autowired
	private AttendanceDAO attendanceDAO;
		
	@Autowired
	public AttendanceServiceImpl(@Qualifier("attendanceDAOImpl") AttendanceDAO theAttendanceDAO) {
		attendanceDAO = theAttendanceDAO;
	}
	
	
	@Override
	@Transactional
	public List<Integer> getAttendanceYears() {
		return attendanceDAO.getAttendanceYears();
	}


	@Override
	@Transactional
	public VacationTotals getVacationTotals(Integer year, Integer employeeId) {
		
		return attendanceDAO.getVacationTotals(year, employeeId);
	}


	@Override
	public List<AttendanceMonthly> getAttendanceMonthly(Integer year, Integer employeeId) {
		
		return attendanceDAO.getAttendanceMonthly(year, employeeId);
	}


	@Override
	public List<CarryOver> getVacationCarryOverDays(Integer year, Integer emplId) {
		// TODO Auto-generated method stub
		return attendanceDAO.getCarryOverDays(year, emplId);
	}


	@Override
	public Double getTotalCarryOverDays(List<CarryOver> carryOvers) {
		
		return carryOvers.stream().mapToDouble(i -> i.getCarryOverDays()).sum();
		
	}
	
	@Override
	public List<LocationMonthly> getLocationMonthly(Integer year, Integer employeeId) {
		
		return attendanceDAO.getLocationMonthly(year, employeeId);
	}

}
