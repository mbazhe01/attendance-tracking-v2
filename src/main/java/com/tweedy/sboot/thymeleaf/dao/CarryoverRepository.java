package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweedy.sboot.thymeleaf.entity.CarryoverRecord;

public interface CarryoverRepository extends JpaRepository<CarryoverRecord, Integer> {

	List<CarryoverRecord> findAllByEmplId(int id);

	CarryoverRecord findAllByEmplIdAndVacationYear(int emplId, int vacationYear);
	
	List<CarryoverRecord> findAllByEmplIdOrderByVacationYearDesc(int id);
	
}
