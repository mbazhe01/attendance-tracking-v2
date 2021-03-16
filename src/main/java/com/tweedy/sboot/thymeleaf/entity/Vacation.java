package com.tweedy.sboot.thymeleaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Vacation class - allocate vacation days by employee
 * 
 * @author mikeba
 *
 */
@Entity
@Table(name = "tb_vacation")
public class Vacation implements InterfaceGetEmployee {
	private int vacationId; // an unique record number
	private int emplId; // id of the employee for whom we allocate vacation days
	private int vacationYear; // the year for which we allocate vacation days
	private int vacationDays; // a number of vacation days allocated for the employee for the allocation year
	private String description;
	private int personalDays; // a number of personal days allocated for the employee for the allocation year
	private int carryOverExpired; // 0 or 1 - indicates if the carryover days for the year expired

	public Vacation() {
	}

	public Vacation(int vacationId, int emplId, int vacationYear, int vacationDays, String description,
			int personalDays, int carryOverExpired) {
		this.vacationId = vacationId;
		this.emplId = emplId;
		this.vacationYear = vacationYear;
		this.vacationDays = vacationDays;
		this.description = description;
		this.personalDays = personalDays;
		this.carryOverExpired = carryOverExpired;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vacation_id")
	public int getVacationId() {
		return vacationId;
	}

	public void setVacationId(int vacationId) {
		this.vacationId = vacationId;
	}

	@Column(name = "empl_id")
	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}

	@Column(name = "vacation_year")
	public int getVacationYear() {
		return vacationYear;
	}

	public void setVacationYear(int vacationYear) {
		this.vacationYear = vacationYear;
	}

	@Column(name = "vacation_days")
	public int getVacationDays() {
		return vacationDays;
	}

	public void setVacationDays(int vacationDays) {
		this.vacationDays = vacationDays;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int extractEmployeeId() {

		return emplId;
	}

	@Column(name = "personal_days")
	public int getPersonalDays() {
		return personalDays;
	}

	public void setPersonalDays(int personalDays) {
		this.personalDays = personalDays;
	}

	@Column(name = "carryover_expired")
	public int getCarryOverExpired() {
		return carryOverExpired;
	}

	public void setCarryOverExpired(int carryOverExpired) {
		this.carryOverExpired = carryOverExpired;
	}

	public VacationTL toVacationTL() {
		return new VacationTL(this.vacationId, this.emplId, this.vacationYear, this.vacationDays, this.description,
				this.personalDays, (this.carryOverExpired == 0) ? false : true);
	}

}// eoc
