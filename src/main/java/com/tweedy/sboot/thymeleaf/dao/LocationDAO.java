package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.Location;

public interface LocationDAO {

	public List<Location> getLocations();

	public void saveLocation(Location theLocation);

	public Location getLocation(int theId);

	public void deleteLocation(int theId);
	
	public Location getDefaultLocation();
	
}
