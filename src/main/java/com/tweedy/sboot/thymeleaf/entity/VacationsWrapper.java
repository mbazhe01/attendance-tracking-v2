package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

public class VacationsWrapper {
	List<VacationTL> vacations;

	public VacationsWrapper() {

	}

	public VacationsWrapper(List<VacationTL> vacations) {
		super();
		this.vacations = vacations;
	}

	public List<VacationTL> getVacations() {
		return vacations;
	}

	public void setVacations(List<VacationTL> vacations) {
		this.vacations = vacations;
	}

	public void addVacation(VacationTL vacation) {
		this.vacations.add(vacation);
	}

	public int getEmployeeId() {
		int emplId = -1;

		if (vacations != null && vacations.size() > 0) {

			for (VacationTL v : vacations) {
				emplId = v.getEmplId();
			}

		}

		return emplId;

	}

}
