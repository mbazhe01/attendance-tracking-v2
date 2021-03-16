package com.tweedy.sboot.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.Location;
import com.tweedy.sboot.thymeleaf.entity.LocationTL;
import com.tweedy.sboot.thymeleaf.service.LocationService;
import com.tweedy.sboot.thymeleaf.service.LocationServiceImpl;

@Controller
@RequestMapping("/locations")
public class LocationController {

	private LocationService locationService;
	
	@Autowired
	public LocationController(@Qualifier("locationServiceImpl") LocationService theLocationService) {
		locationService = theLocationService;
	}
	
	// add mapping 
	@GetMapping("/list")
	public String getLocations(Model theModel) {
			
		List<Location> theLocations = locationService.findAll();
			
		theModel.addAttribute("locations", theLocations);
			return "locations/list-locations";
			
	}
	
	// add location
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
			
		LocationTL theLocation = new LocationTL();
			
		theModel.addAttribute("location", theLocation);
		return "locations/location-form";
			
	}
	
	// save location
	@PostMapping("/save")
	public String saveLocation( @ModelAttribute("locationTL") LocationTL theLocation) {
				
		
		Location location = new Location();
		
		location.setLocationId(theLocation.getLocationId());
		location.setLocationName(theLocation.getLocationName());
		
		if (theLocation.isDefaultLocation()==true)		
			location.setDefaultLocation(1);
		else 
			location.setDefaultLocation(0);
		
		locationService.save(location);
			
		return "redirect:/locations/list";
				
	}
	
	// add employee
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("locationId") int theId,
			Model theModel) {
			
			Location theLocation = locationService.findById(theId);
			
			LocationTL location = new LocationTL();
			
			location.setLocationId(theLocation.getLocationId());
			location.setLocationName(theLocation.getLocationName());
			
			if (theLocation.getDefaultLocation()==1)
				location.setDefaultLocation(true);
			else
				location.setDefaultLocation(false);
			
			
			theModel.addAttribute("location", location);
			return "locations/location-form";
				
	}
	
	// delete location
	@GetMapping("/delete")
	public String delete(@RequestParam("locationId") int theId) {
				
		locationService.deleteById(theId);
			
		return "redirect:/locations/list";
				
	}
	
}
