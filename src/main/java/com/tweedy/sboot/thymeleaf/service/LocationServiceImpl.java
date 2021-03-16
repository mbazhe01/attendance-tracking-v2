package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tweedy.sboot.thymeleaf.dao.LocationDAO;
import com.tweedy.sboot.thymeleaf.dao.LocationRepository;
import com.tweedy.sboot.thymeleaf.entity.Location;

@Service
public class LocationServiceImpl implements LocationService {

	// need to inject location dao
	@Autowired
	private LocationDAO locationDAO;
		
	@Autowired
	public LocationServiceImpl(@Qualifier("locationDAOImpl") LocationDAO theLocationDAO) {
		locationDAO = theLocationDAO;
	}
	
	
	@Override
	@Transactional
	public List<Location> findAll() {
		return locationDAO.getLocations();
	}

	@Override
	@Transactional
	public Location findById(int theId) {
		// TODO Auto-generated method stub
		return locationDAO.getLocation(theId);
	}

	@Override
	@Transactional
	public void save(Location theLocation) {
		locationDAO.saveLocation(theLocation);

	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		
		locationDAO.deleteLocation(theId);
	}


	@Override
	public Location findDefaultLocation() {
		// TODO Auto-generated method stub
		return locationDAO.getDefaultLocation();
	}

}
