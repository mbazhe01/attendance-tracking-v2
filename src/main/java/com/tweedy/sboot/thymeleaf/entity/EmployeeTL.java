package com.tweedy.sboot.thymeleaf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * EmployeeTL class for Thymeleaf activeStatus filed made boolean so we can use
 * check box in Thymeleaf
 * 
 * @author mikeba
 *
 */
@Entity
@Table(name = "tb_employee")
public class EmployeeTL {

	private int emplId;
	private Integer departmentId;
	
	private String firstName;
	private String lastName;
	private String middleName;
	private String phoneIntercomeExt;
	private String description;
	private boolean activeStatus;
	private String startDate;
	private String resignDate;
	private Integer defaultVacationDays;

	// constructor 1
	public EmployeeTL() {

	}

	// constructor 2
	public EmployeeTL(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;

	}

	// constructor 3
	public EmployeeTL(int emplId, String firstName, String lastName) {

		this.emplId = emplId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public EmployeeTL(int emplId, Integer departmentId, String firstName, String lastName, String middleName,
			String phoneIntercomeExt, String description, boolean activeStatus, String startDate, String resignDate,
			Integer defaultVacationDays) {

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

	@NotNull
	@Size(min = 2, max = 45)
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
	public boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
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
		return "EmployeeTL [emplId=" + emplId + ", departmentId=" + departmentId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName + ", phoneIntercomeExt=" + phoneIntercomeExt
				+ ", description=" + description + ", activeStatus=" + activeStatus + ", startDate=" + startDate
				+ ", resignDate=" + resignDate + "]";
	}

}