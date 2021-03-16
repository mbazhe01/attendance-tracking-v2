package com.tweedy.sboot.thymeleaf.entity;

public class EmployeeShort {
	private int emplId;
	private String name;
	public EmployeeShort(int empl_id, String name) {
		this.emplId = empl_id;
		this.name = name;
	}
	
	public int getEmplId() {
		return emplId;
	}
	public void setEmpl_id(int empl_id) {
		this.emplId = empl_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
