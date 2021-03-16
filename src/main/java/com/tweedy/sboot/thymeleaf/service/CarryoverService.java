package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweedy.sboot.thymeleaf.dao.CarryoverRepository;
import com.tweedy.sboot.thymeleaf.entity.CarryoverRecord;

@Service
public class CarryoverService {

	@Autowired 
	CarryoverRepository carryoverRepo;
	
	public List<CarryoverRecord> findAllByEmplId(int id){
		return carryoverRepo.findAllByEmplId(id);
	}

	public List<CarryoverRecord> findAllByEmplIdOrderByVacationYearDesc(int id) {
		return carryoverRepo.findAllByEmplIdOrderByVacationYearDesc(id);
	}
	
	public void save(CarryoverRecord c) {
		
		carryoverRepo.save(c);
		
	}

	public CarryoverRecord findAllByEmplIdAndVacationYear(int emplId, int vacationYear) {
		
		return carryoverRepo.findAllByEmplIdAndVacationYear(emplId, vacationYear);
	}

	public void delete(CarryoverRecord r) {
		carryoverRepo.delete(r);
		
	}
	
}
