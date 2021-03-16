package com.tweedy.sboot.thymeleaf.entity;

public class CarryOver {
	private int vacYear;
	private int vacDaysAllowed;
	private Double vacDaysTaken;
	private Double carryOverDays;
	private int carryOverExpired;
	private Double usedCarryOverDays;

	

	public CarryOver(int vacYear, int vacDaysAllowed, Double vacDaysTaken, Double carryOverDays, int carryOverExpired,
			Double usedCarryOverDays) {
		super();
		this.vacYear = vacYear;
		this.vacDaysAllowed = vacDaysAllowed;
		this.vacDaysTaken = vacDaysTaken;
		this.carryOverDays = carryOverDays;
		this.carryOverExpired = carryOverExpired;
		this.usedCarryOverDays = usedCarryOverDays;
	}


	public CarryOver() {

	}

		

	public Double getUsedCarryOverDays() {
		return usedCarryOverDays;
	}

	public void setUsedCarryOverDays(Double usedCarryOverDays) {
		this.usedCarryOverDays = usedCarryOverDays;
	}

	public int getCarryOverExpired() {
		return carryOverExpired;
	}

	public void setCarryOverExpired(int carryOverExpired) {
		this.carryOverExpired = carryOverExpired;
	}

	public int getVacYear() {
		return vacYear;
	}

	public void setVacYear(int vacYear) {
		this.vacYear = vacYear;
	}

	public int getVacDaysAllowed() {
		return vacDaysAllowed;
	}

	public void setVacDaysAllowed(int vacDaysAllowed) {
		this.vacDaysAllowed = vacDaysAllowed;
	}

	public Double getVacDaysTaken() {
		return vacDaysTaken;
	}

	public void setVacDaysTaken(Double vacDaysTaken) {
		this.vacDaysTaken = vacDaysTaken;
	}

	public Double getCarryOverDays() {
		return carryOverDays;
	}

	public void setCarryOverDays(Double carryOverDays) {
		this.carryOverDays = carryOverDays;
	}

	public void setZeroCarryOverDaysToNull() {
		if (this.carryOverDays == 0)
			this.carryOverDays = null;
	}

}
