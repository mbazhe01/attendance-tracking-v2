package com.tweedy.sboot.thymeleaf.entity;

/**
 * AttendanceMonthly class is used for reporting
 * 
 * @author mikeba
 *
 */
public class AttendanceMonthly {
	private String attendanceCode;
	private Integer janDays;
	private Integer febDays;
	private Integer marDays;
	private Integer aprDays;
	private Integer mayDays;
	private Integer junDays;
	private Integer julDays;
	private Integer augDays;
	private Integer sepDays;
	private Integer octDays;
	private Integer novDays;
	private Integer decDays;
	private Integer total;

	private String attendanceCodeDescription;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public AttendanceMonthly() {
	}

	public String getAttendanceCode() {
		return attendanceCode;
	}

	public void setAttendanceCode(String attendanceCode) {
		this.attendanceCode = attendanceCode;
	}

	public Integer getJanDays() {
		return janDays;
	}

	public void setJanDays(Integer janDays) {
		this.janDays = janDays;
	}

	public Integer getFebDays() {
		return febDays;
	}

	public void setFebDays(Integer febDays) {
		this.febDays = febDays;
	}

	public Integer getMarDays() {
		return marDays;
	}

	public void setMarDays(Integer marDays) {
		this.marDays = marDays;
	}

	public Integer getAprDays() {
		return aprDays;
	}

	public void setAprDays(Integer aprDays) {
		this.aprDays = aprDays;
	}

	public Integer getMayDays() {
		return mayDays;
	}

	public void setMayDays(Integer mayDays) {
		this.mayDays = mayDays;
	}

	public Integer getJunDays() {
		return junDays;
	}

	public void setJunDays(Integer junDays) {
		this.junDays = junDays;
	}

	public Integer getJulDays() {
		return julDays;
	}

	public void setJulDays(Integer julDays) {
		this.julDays = julDays;
	}

	public Integer getAugDays() {
		return augDays;
	}

	public void setAugDays(Integer augDays) {
		this.augDays = augDays;
	}

	public Integer getSepDays() {
		return sepDays;
	}

	public void setSepDays(Integer sepDays) {
		this.sepDays = sepDays;
	}

	public Integer getOctDays() {
		return octDays;
	}

	public void setOctDays(Integer octDays) {
		this.octDays = octDays;
	}

	public Integer getNovDays() {
		return novDays;
	}

	public void setNovDays(Integer novDays) {
		this.novDays = novDays;
	}

	public Integer getDecDays() {
		return decDays;
	}

	public void setDecDays(Integer decDays) {
		this.decDays = decDays;
	}

	public String getAttendanceCodeDescription() {
		return attendanceCodeDescription;
	}

	public void setAttendanceCodeDescription(String attendanceCodeDescription) {
		this.attendanceCodeDescription = attendanceCodeDescription;
	}

}
