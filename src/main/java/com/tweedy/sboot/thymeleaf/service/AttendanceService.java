package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.AttendanceMonthly;
import com.tweedy.sboot.thymeleaf.entity.CarryOver;
import com.tweedy.sboot.thymeleaf.entity.LocationMonthly;
import com.tweedy.sboot.thymeleaf.entity.VacationTotals;

public interface AttendanceService {
	
	public List<Integer> getAttendanceYears();
	
	public VacationTotals getVacationTotals(Integer year, Integer employeeId);
	
	public List<AttendanceMonthly> getAttendanceMonthly(Integer year, Integer employeeId);
	
	public List<CarryOver> getVacationCarryOverDays(Integer year, Integer emplId);
	
	public Double getTotalCarryOverDays(List<CarryOver> carryOvers);
	
	public List<LocationMonthly> getLocationMonthly(Integer year, Integer employeeId);
}
