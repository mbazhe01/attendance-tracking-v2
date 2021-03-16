package com.tweedy.sboot.thymeleaf.entity;

/**
 * VacationTotals class is used for reporting
 * 
 * @author mikeba
 *
 */
public class VacationTotals {
	private String lastName;
	private String firstName;
	private Integer vacationYear;
	private Integer vacationDaysAllowed;
	private Integer personalDaysAllowed;
	private Integer personalDaysTaken;
	private Double vacationDaysTaken;
	private Double vacationDaysLeft;
	private Integer personalDaysLeft;

	private Double usedCarryOverDays;
	private Double carryOverDays;
	private Double carryOverDaysLeft;
	private Double vacAndCarryOverDaysAllowed;
	private Double vacAndCarryOverDaysLeft;

	public VacationTotals() {
	}

	public VacationTotals(String lastName, String firstName, Integer vacationYear, Integer vacationDaysAllowed,
			Integer personalDaysAllowed, Integer personalDaysTaken, Double vacationDaysTaken, Double vacationDaysLeft,
			Integer personalDaysLeft) {

		this.lastName = lastName;
		this.firstName = firstName;
		this.vacationYear = vacationYear;
		this.vacationDaysAllowed = vacationDaysAllowed;
		this.personalDaysAllowed = personalDaysAllowed;
		this.personalDaysTaken = personalDaysTaken;
		this.vacationDaysTaken = vacationDaysTaken;
		this.vacationDaysLeft = vacationDaysLeft;
		this.personalDaysLeft = personalDaysLeft;
	}

	public VacationTotals(String lastName, String firstName, Integer vacationYear, Integer vacationDaysAllowed,
			Integer personalDaysAllowed, Integer personalDaysTaken, Double vacationDaysTaken, Double vacationDaysLeft,
			Integer personalDaysLeft, Double usedCarryOverDays, Double carryOverDays, Double carryOverDaysLeft,
			Double vacAndCarryOverDaysAllowed, Double vacAndCarryOverDaysLeft) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.vacationYear = vacationYear;
		this.vacationDaysAllowed = vacationDaysAllowed;
		this.personalDaysAllowed = personalDaysAllowed;
		this.personalDaysTaken = personalDaysTaken;
		this.vacationDaysTaken = vacationDaysTaken;
		this.vacationDaysLeft = vacationDaysLeft;
		this.personalDaysLeft = personalDaysLeft;
		this.usedCarryOverDays = usedCarryOverDays;
		this.carryOverDays = carryOverDays;
		this.carryOverDaysLeft = carryOverDaysLeft;
		this.vacAndCarryOverDaysAllowed = vacAndCarryOverDaysAllowed;
		this.vacAndCarryOverDaysLeft = vacAndCarryOverDaysLeft;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getVacationYear() {
		return vacationYear;
	}

	public void setVacationYear(Integer vacationYear) {
		this.vacationYear = vacationYear;
	}

	public Integer getVacationDaysAllowed() {
		return vacationDaysAllowed;
	}

	public void setVacationDaysAllowed(Integer vacationDaysAllowed) {
		this.vacationDaysAllowed = vacationDaysAllowed;
	}

	public Integer getPersonalDaysAllowed() {
		return personalDaysAllowed;
	}

	public void setPersonalDaysAllowed(Integer personalDaysAllowed) {
		this.personalDaysAllowed = personalDaysAllowed;
	}

	public Integer getPersonalDaysTaken() {
		return personalDaysTaken;
	}

	public void setPersonalDaysTaken(Integer personalDaysTaken) {
		this.personalDaysTaken = personalDaysTaken;
	}

	public Double getVacationDaysTaken() {
		return vacationDaysTaken;
	}

	public void setVacationDaysTaken(Double vacationDaysTaken) {
		this.vacationDaysTaken = vacationDaysTaken;
	}

	public Double getVacationDaysLeft() {
		return vacationDaysLeft;
	}

	public void setVacationDaysLeft(Double vacationDaysLeft) {
		this.vacationDaysLeft = vacationDaysLeft;
	}

	public Integer getPersonalDaysLeft() {
		return personalDaysLeft;
	}

	public void setPersonalDaysLeft(Integer personalDaysLeft) {
		this.personalDaysLeft = personalDaysLeft;
	}

	public Double getUsedCarryOverDays() {
		return usedCarryOverDays;
	}

	public void setUsedCarryOverDays(Double usedCarryOverDays) {
		this.usedCarryOverDays = usedCarryOverDays;
	}

	public Double getCarryOverDaysLeft() {
		return carryOverDaysLeft;
	}

	public void setCarryOverDaysLeft(Double carryOverDaysLeft) {
		this.carryOverDaysLeft = carryOverDaysLeft;
	}

	public Double getVacAndCarryOverDaysAllowed() {
		return vacAndCarryOverDaysAllowed;
	}

	public void setVacAndCarryOverDaysAllowed(Double vacAndCarryOverDaysAllowed) {
		this.vacAndCarryOverDaysAllowed = vacAndCarryOverDaysAllowed;
	}

	public Double getVacAndCarryOverDaysLeft() {
		return vacAndCarryOverDaysLeft;
	}

	public void setVacAndCarryOverDaysLeft(Double vacAndCarryOverDaysLeft) {
		this.vacAndCarryOverDaysLeft = vacAndCarryOverDaysLeft;
	}

	public Double getCarryOverDays() {
		return carryOverDays;
	}

	public void setCarryOverDays(Double carryOverDays) {
		this.carryOverDays = carryOverDays;
	}

}
