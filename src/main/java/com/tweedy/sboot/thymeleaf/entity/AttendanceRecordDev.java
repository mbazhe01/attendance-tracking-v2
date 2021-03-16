package com.tweedy.sboot.thymeleaf.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_attendance")
public class AttendanceRecordDev {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rec_id")
	private int recId;
	
	@Column(name="rec_date")
	private Timestamp recDate;
			
	@Column(name="attendance_code_id")
	private Integer attendanceCodeId;
	
	@Column(name="location_id")
	private Integer locationId;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="empl_id")
	private EmployeeDev employee;
	
	
	// constructors
	public AttendanceRecordDev () {}

	public AttendanceRecordDev(Timestamp recDate, Integer attendanceCodeId, Integer locationId, String description) {

		this.recDate = recDate;
		this.attendanceCodeId = attendanceCodeId;
		this.locationId = locationId;
		this.description = description;
	}

	// getters & setters
	
	public int getRecId() {
		return recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public Timestamp getRecDate() {
		return recDate;
	}

	public void setRecDate(Timestamp recDate) {
		this.recDate = recDate;
	}
	
	public Integer getAttendanceCodeId() {
		return attendanceCodeId;
	}

	public void setAttendanceCodeId(Integer attendanceCodeId) {
		this.attendanceCodeId = attendanceCodeId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EmployeeDev getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDev employee) {
		this.employee = employee;
	}

	// to use as compare method
	public String getName() {
		return employee.getLastName() + ", " + employee.getFirstName();
	}
			

}
