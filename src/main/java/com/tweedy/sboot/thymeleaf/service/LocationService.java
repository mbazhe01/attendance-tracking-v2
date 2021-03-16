package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.Location;


public interface LocationService {

	public List<Location> findAll();
	
	public Location findById(int theId);
	
	public void save(Location theLocation);
	
	public void deleteById(int theId);
	
	public Location findDefaultLocation();
}
