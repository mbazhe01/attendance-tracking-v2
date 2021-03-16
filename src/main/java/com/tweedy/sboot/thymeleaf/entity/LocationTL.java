package com.tweedy.sboot.thymeleaf.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class created to make use of Thymeleaf
 * checkboxes. Field deafultLocation is made boolean
 * so we can read checkbox values back from HTML page
 * @author mikeba
 *
 */
public class LocationTL {

	private int locationId;
	@NotNull
	@Size(min=2, message=" length should be at least 2 chars")
    private String locationName;
    private boolean defaultLocation; 
    
    public LocationTL() {}

	public LocationTL(int locationId, String locationName, boolean defaultLocation) {
		this.locationId = locationId;
		this.locationName = locationName;
		this.defaultLocation = defaultLocation;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public boolean isDefaultLocation() {
		return defaultLocation;
	}

	public void setDefaultLocation(boolean defaultLocation) {
		this.defaultLocation = defaultLocation;
	}

	@Override
	public String toString() {
		return "LocationTL [locationId=" + locationId + ", locationName=" + locationName + ", defaultLocation="
				+ defaultLocation + "]";
	}
	
	
    
    
	
}
