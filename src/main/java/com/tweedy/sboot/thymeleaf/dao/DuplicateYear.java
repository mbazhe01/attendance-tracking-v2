package com.tweedy.sboot.thymeleaf.dao;

import java.util.Optional;

import com.tweedy.sboot.thymeleaf.entity.CarryoverRecord;

import lombok.Data;

@Data
public class DuplicateYear {
	private boolean found;
	private int dupYear;
	
	public DuplicateYear(boolean found, int dupYear) {
		super();
		this.found = found;
		this.dupYear = dupYear;
	}
	
	
}
