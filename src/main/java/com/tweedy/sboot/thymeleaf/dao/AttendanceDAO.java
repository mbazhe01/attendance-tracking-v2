package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.AttendanceMonthly;
import com.tweedy.sboot.thymeleaf.entity.CarryOver;
import com.tweedy.sboot.thymeleaf.entity.LocationMonthly;
import com.tweedy.sboot.thymeleaf.entity.VacationTotals;

public interface AttendanceDAO {
	public List<Integer> getAttendanceYears();
	
	public VacationTotals getVacationTotals(Integer year, Integer emplId);
	
	public List<AttendanceMonthly> getAttendanceMonthly(Integer year, Integer empId);
	
	public List<CarryOver> getCarryOverDays(Integer year, int emplId);
	
	public List<LocationMonthly> getLocationMonthly(Integer year, Integer empId);
}
