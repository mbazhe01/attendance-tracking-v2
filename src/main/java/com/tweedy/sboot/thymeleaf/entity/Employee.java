package com.tweedy.sboot.thymeleaf.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_employee")
public class Employee {

	private int emplId;
	private Integer departmentId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String phoneIntercomeExt;
	private String description;
	private int activeStatus;
	private String startDate;
	private String resignDate;
	private Integer defaultVacationDays;

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "empl_id")
	private List<AttendanceRecord> attendanceRecords;

	// constructor 1
	public Employee() {

	}

	// constructor 2
	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;

	}

	// constructor 3
	public Employee(int emplId, String firstName, String lastName) {

		this.emplId = emplId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Employee(int emplId, Integer departmentId, String firstName, String lastName, String middleName,
			String phoneIntercomeExt, String description, int activeStatus, String startDate, String resignDate,
			int defaultVacationDays) {

		this.emplId = emplId;
		this.departmentId = departmentId;

		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.phoneIntercomeExt = phoneIntercomeExt;
		this.description = description;
		this.activeStatus = activeStatus;
		this.startDate = startDate;
		this.resignDate = resignDate;
		this.defaultVacationDays = defaultVacationDays;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empl_id")
	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int id) {
		this.emplId = id;
	}

	@Size(min = 2, max = 10)
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "department_id")
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "middle_name", nullable = true)
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "phone_intercom_ext", nullable = true)
	public String getPhoneIntercomeExt() {
		return phoneIntercomeExt;
	}

	public void setPhoneIntercomeExt(String phoneIntercomeExt) {
		this.phoneIntercomeExt = phoneIntercomeExt;
	}

	@Column(name = "description", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "active_status", nullable = true)
	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Column(name = "start_date", nullable = true)
	public String getStartDate() {

		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name = "resign_date", nullable = true)
	public String getResignDate() {
		return resignDate;
	}

	public void setResignDate(String resignDate) {
		this.resignDate = resignDate;
	}

	@Column(name = "default_vacation_days", nullable = true)
	public Integer getDefaultVacationDays() {
		return defaultVacationDays;
	}

	public void setDefaultVacationDays(Integer defaultVacationDays) {
		this.defaultVacationDays = defaultVacationDays;
	}

	@Override
	public String toString() {
		return "Employee [id=" + emplId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public EmployeeTL convertToEmployeeTL() {

		EmployeeTL etl = new EmployeeTL();

		etl.setEmplId(this.getEmplId());
		etl.setDepartmentId(this.getDepartmentId());
		etl.setFirstName(this.getFirstName());
		etl.setLastName(this.getLastName());
		etl.setMiddleName(this.getMiddleName());
		etl.setPhoneIntercomeExt(this.getPhoneIntercomeExt());
		etl.setDescription(this.getDescription());

		if (this.getActiveStatus() == 1)
			etl.setActiveStatus(true);
		else
			etl.setActiveStatus(false);

		etl.setStartDate(this.startDate);
		etl.setResignDate(this.getResignDate());
		etl.setDefaultVacationDays(this.defaultVacationDays);
		return etl;
	}

	// Convenience method to add attendance record
	public void addAttendanceRecord(AttendanceRecord attendanceRecord) {
		if (attendanceRecords == null)
			attendanceRecords = new ArrayList<>();
		attendanceRecords.add(attendanceRecord);

		attendanceRecord.setEmployee(this);
	}

}// eoc