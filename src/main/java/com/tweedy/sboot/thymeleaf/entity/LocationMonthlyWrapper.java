package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

/**
 * the wrapper class has been created
 * to pass a list to Thymeleaf template
 * @author mikeba
 *
 */
public class LocationMonthlyWrapper {
	List<LocationMonthly> records;

	public LocationMonthlyWrapper(List<LocationMonthly> records) {
		super();
		this.records = records;
	}

	public List<LocationMonthly> getRecords() {
		return records;
	}

	public void setRecords(List<LocationMonthly> records) {
		this.records = records;
	}
	
}
