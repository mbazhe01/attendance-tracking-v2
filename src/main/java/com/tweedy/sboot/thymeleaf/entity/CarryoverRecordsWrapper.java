package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;

import lombok.Data;

@Data
public class CarryoverRecordsWrapper {

	List<CarryoverRecord> carryovers;

	public void addCarryoverRecord(CarryoverRecord carryOverRecord) {
		// add on top of the list
		this.carryovers.add(0, carryOverRecord);
	}

	public CarryoverRecordsWrapper(List<CarryoverRecord> carryOvers) {

		this.carryovers = carryOvers;

	}

	// utility function
	public int getEmployeeId() {
		int emplId = -1;

		if (carryovers != null && carryovers.size() > 0) {

			for (CarryoverRecord c : carryovers) {
				emplId = c.getEmplId();
			}

		}

		return emplId;

	}

	// non field constructor
	public CarryoverRecordsWrapper() {

	}

	public int deleteCarryOver(int id) {

		int removedYear = 0;

		if (carryovers != null) {

			int index = -1;

			for (int i = 0; i < carryovers.size(); i++) {

				if (carryovers.get(i).getId() == id) {
					index = i;
					removedYear = carryovers.get(i).getVacationYear();
					break;
				}

			}

			if (index != -1) {
				carryovers.remove(index);
			}

		}
		return removedYear;

	}// eof

	public boolean contains(CarryoverRecord c) {

		if (carryovers.contains(c))
			return true;
		else
			return false;

	}

}
