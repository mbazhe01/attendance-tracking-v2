package com.tweedy.sboot.thymeleaf.entity;

public class AttendanceSummaryParamSelector {

	private Integer year;
	private Integer emplId;
	public AttendanceSummaryParamSelector(Integer year, Integer emplId) {
		this.year = year;
		this.emplId = emplId;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getEmplId() {
		return emplId;
	}
	public void setEmplId(Integer emplId) {
		this.emplId = emplId;
	}
	
}
