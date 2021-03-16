package com.tweedy.sboot.thymeleaf.entity;

public class VacationTL {

	private int vacationId; // an unique record number
	private int emplId; // id of the employee for whom we allocate vacation days
	private int vacationYear; // the year for which we allocate vacation days
	private int vacationDays; // a number of vacation days allocated for the employee for the allocation year
	private String description;
	private int personalDays; // a number of personal days allocated for the employee for the allocation year
	private boolean carryOverExpired; // 0 or 1 - indicates if the carryover days for the year expired

	public VacationTL() {
	}

	public VacationTL(int vacationId, int emplId, int vacationYear, int vacationDays, String description,
			int personalDays, boolean carryOverExpired) {
		super();
		this.vacationId = vacationId;
		this.emplId = emplId;
		this.vacationYear = vacationYear;
		this.vacationDays = vacationDays;
		this.description = description;
		this.personalDays = personalDays;
		this.carryOverExpired = carryOverExpired;
	}

	public int getVacationId() {
		return vacationId;
	}

	public void setVacationId(int vacationId) {
		this.vacationId = vacationId;
	}

	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}

	public int getVacationYear() {
		return vacationYear;
	}

	public void setVacationYear(int vacationYear) {
		this.vacationYear = vacationYear;
	}

	public int getVacationDays() {
		return vacationDays;
	}

	public void setVacationDays(int vacationDays) {
		this.vacationDays = vacationDays;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPersonalDays() {
		return personalDays;
	}

	public void setPersonalDays(int personalDays) {
		this.personalDays = personalDays;
	}

	public boolean isCarryOverExpired() {
		return carryOverExpired;
	}

	public void setCarryOverExpired(boolean carryOverExpired) {
		this.carryOverExpired = carryOverExpired;
	}

	public Vacation toVacation() {
		return new Vacation(this.vacationId, this.emplId, this.vacationYear, this.vacationDays, this.description,
				this.personalDays, (this.carryOverExpired == false) ? 0 : 1

		);
	}

}
