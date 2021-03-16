package com.tweedy.sboot.thymeleaf.entity;

public class LocationMonthly {
	private String locationName;
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
	private Integer totalByLocation;
	
	public LocationMonthly() {}

	public LocationMonthly(String locationName, Integer janDays, Integer febDays, Integer marDays, Integer aprDays,
			Integer mayDays, Integer junDays, Integer julDays, Integer augDays, Integer sepDays, Integer octDays,
			Integer novDays, Integer decDays, Integer totalByLocation) {
		this.locationName = locationName;
		this.janDays = janDays;
		this.febDays = febDays;
		this.marDays = marDays;
		this.aprDays = aprDays;
		this.mayDays = mayDays;
		this.junDays = junDays;
		this.julDays = julDays;
		this.augDays = augDays;
		this.sepDays = sepDays;
		this.octDays = octDays;
		this.novDays = novDays;
		this.decDays = decDays;
		this.totalByLocation = totalByLocation;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
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

	public Integer getTotalByLocation() {
		return totalByLocation;
	}

	public void setTotalByLocation(Integer totalByLocation) {
		this.totalByLocation = totalByLocation;
	}
	
}
