package com.tweedy.sboot.thymeleaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_location")
public class Location {
	
	private int locationId;
	private String locationName;
    private int defaultLocation; 
    
    public Location() {}

	public Location(int locationId, String locationName, int defaultLocation) {
		
		this.locationId = locationId;
		this.locationName = locationName;
		this.defaultLocation = defaultLocation;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="location_id")
	public int getLocationId() {
		return locationId;
	}

	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	@Column(name = "location_name", nullable = false)
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Column(name = "default_location", nullable = false)
	public int getDefaultLocation() {
		return defaultLocation;
	}

	public void setDefaultLocation(int defaultLocation) {
		this.defaultLocation = defaultLocation;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", locationName=" + locationName + ", defaultLocation="
				+ defaultLocation + "]";
	}
    
	
    

}
