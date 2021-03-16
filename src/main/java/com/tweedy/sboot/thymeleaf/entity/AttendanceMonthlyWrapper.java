package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;
/**
 * the wrapper class has been created
 * to pass a list to Thymeleaf template
 * @author mikeba
 *
 */
public class AttendanceMonthlyWrapper {

	List<AttendanceMonthly> records;

	public AttendanceMonthlyWrapper(List<AttendanceMonthly> records) {

		this.records = records;
	}

	public List<AttendanceMonthly> getRecords() {
		return records;
	}

	public void setRecords(List<AttendanceMonthly> records) {
		this.records = records;
	}
	
	
	
}
