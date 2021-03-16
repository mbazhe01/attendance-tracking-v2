package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

public class CarryOverWrapper {

	List<CarryOver> records;

	public CarryOverWrapper(List<CarryOver> carryoverdays) {
		
		this.records = carryoverdays;
	}

	public List<CarryOver> getRecords() {
		return records;
	}

	public void setRecords(List<CarryOver> records) {
		this.records = records;
	}

	
	
}
