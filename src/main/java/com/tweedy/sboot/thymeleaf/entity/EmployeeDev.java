package com.tweedy.sboot.thymeleaf.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;

import com.tweedy.sboot.thymeleaf.annotation.ValidDate;

@Entity
@Table(name = "tb_employee")
public class EmployeeDev {
	
	@Transient
	@Value("${media.deafult.picture}")
	private String defaultPicture;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empl_id")
	private int emplId;

	@Column(name = "department_id")
	private Integer departmentId;

	@Size(min = 2, max = 36, message = "First name should be 2 to 36 chars.")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Size(min = 2, max = 36, message = "Last name should be 2 to 36 chars.")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "middle_name", nullable = true)
	private String middleName;

	@Pattern(regexp = "^[0-9 ]*$", message = "Intercom extension should be numbers only.")
	@Column(name = "phone_intercom_ext", nullable = true)
	private String phoneIntercomeExt;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "active_status", nullable = true)
	private int activeStatus;

	@Transient
	private boolean activeStatusBool; // because in Thymeleaf check boxes take boolean values

	@Column(name = "start_date", nullable = true)
	@ValidDate
	private String startDate;

	@Column(name = "resign_date", nullable = true)
	@ValidDate
	private String resignDate;

	@Column(name = "default_vacation_days", nullable = true)
	@Range(min = 10, max = 50, message = "Default vacation Number is between 10 and 50.")
	private String defaultVacationDays = "";

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JoinColumn(name = "empl_id")
	private List<AttendanceRecordDev> attendanceRecords;

	@OneToMany(mappedBy = "employee", cascade = { CascadeType.ALL
			// CascadeType.DETACH,
			// CascadeType.MERGE, CascadeType.PERSIST,
			// CascadeType.REFRESH, CascadeType.REMOVE
	}, fetch = FetchType.LAZY)
	private List<Address> addresses;

	@OneToMany(mappedBy = "employee", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<NoteDev> notes;

	@OneToMany(mappedBy = "employee", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<Email> emails;

	@Valid
	@OneToMany(mappedBy = "employee", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<Phone> phones;

	@Column(name = "created_at", updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at", updatable = true)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Column(name = "created_user")
	private String createdUser;

	@Column(name = "updated_user")
	private String updatedUser;
	
	@Column(name = "image")
	private String image = defaultPicture;

	// constructor 1
	public EmployeeDev() {

	}

	// constructor 2
	public EmployeeDev(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;

	}

	// constructor 3
	public EmployeeDev(int emplId, String firstName, String lastName) {

		this.emplId = emplId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public EmployeeDev(int emplId, Integer departmentId, String firstName, String lastName, String middleName,
			String phoneIntercomeExt, String description, int activeStatus, String startDate, String resignDate,
			String defaultVacationDays) {

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

	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int id) {
		this.emplId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhoneIntercomeExt() {
		return phoneIntercomeExt;
	}

	public void setPhoneIntercomeExt(String phoneIntercomeExt) {
		this.phoneIntercomeExt = phoneIntercomeExt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public boolean getActiveStatusBool() {
		return activeStatusBool;
	}

	public void setActiveStatusBool(boolean activeStatusBool) {
		this.activeStatusBool = activeStatusBool;
	}

	public String getStartDate() {

		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getResignDate() {
		return resignDate;
	}

	public void setResignDate(String resignDate) {
		this.resignDate = resignDate;
	}

	public String getDefaultVacationDays() {
		return defaultVacationDays;
	}

	public void setDefaultVacationDays(String defaultVacationDays) {
		this.defaultVacationDays = defaultVacationDays;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<NoteDev> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteDev> notes) {
		this.notes = notes;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	public void activeStatusFromBoolToInt() {

		if (this.activeStatusBool == true)
			this.activeStatus = 1;
		else
			this.activeStatus = 0;

	}

	public void activeStatusFromIntToBool() {

		if (this.activeStatus == 1)
			this.activeStatusBool = true;
		else
			this.activeStatusBool = false;

	}

	public void syncFromTL() {
		if (this.activeStatusBool == true)
			this.activeStatus = 1;
		else
			this.activeStatus = 0;

		if (this.startDate.equals(""))
			this.startDate = null;

		// remove all non number chars from the end of the string
		this.startDate = rempveNonDigitsFromEnd(this.startDate);

		if (this.resignDate.equals(""))
			this.resignDate = null;

		// remove all non number chars from the end of the string
		this.resignDate = rempveNonDigitsFromEnd(this.resignDate);
	}

	String rempveNonDigitsFromEnd(String s) {
		String newStr = s;
		char c;
		if (s != null) {

			c = newStr.charAt(newStr.length() - 1);

			while (!Character.isDigit(c)) {
				StringBuffer sb = new StringBuffer(newStr);
				sb.deleteCharAt(sb.length() - 1);
				newStr = sb.toString();
				c = newStr.charAt(newStr.length() - 1);
			}

		}

		return newStr;
	}

	public void addAddress() {
		if (addresses == null)
			addresses = new ArrayList<>();

		addresses.add(new Address());
	}

	// Convenience method to add attendance record
	public void addAttendanceRecord(AttendanceRecordDev attendanceRecord) {
		if (attendanceRecords == null)
			attendanceRecords = new ArrayList<>();
		attendanceRecords.add(attendanceRecord);

		attendanceRecord.setEmployee(this);
	}

	public void addPhone() {
		if (phones == null)
			phones = new ArrayList<>();
		String fakeId = "phone" + phones.size();
		phones.add(new Phone(fakeId));

	}

	public void addNote() {
		if (notes == null)
			notes = new ArrayList<>();
		notes.add(new NoteDev());

	}

	public void assignFakeIds() {

		if (phones != null) {
			int fakeId = 0;
			for (Phone p : phones) {
				p.setFakeId("phone" + fakeId++);
			}
		}

		if (addresses != null) {
			int fakeId = 0;
			for (Address a : addresses) {
				a.setIdTag("addr" + fakeId++);
			}
		}
		
		if (notes != null) {
			int fakeId = 0;
			for (NoteDev n : notes) {
				n.setIdTag("addr" + fakeId++);
			}
		}

	}

	public void purgeEmptyPhones() {
		List<Phone> notEmptyPhones = new ArrayList<>();

		if (phones != null) {
			for (Phone p : phones) {
				if (!p.isEmpty())
					notEmptyPhones.add(p);
			}

			this.setPhones(notEmptyPhones);
		}

	}

	public void purgeEmptyAddresses() {
		List<Address> notEmptyAddrs = new ArrayList<>();

		if (addresses != null) {
			for (Address a : addresses) {
				if (!a.isEmpty())
					notEmptyAddrs.add(a);
			}

			this.setAddresses(notEmptyAddrs);
		}
	}
	
	public void purgeEmptyNotes() {
		List<NoteDev> notEmptyNotes = new ArrayList<>();

		if (notes != null) {
			for (NoteDev n : notes) {
				if (!n.isEmpty())
					notEmptyNotes.add(n);
			}

			this.setNotes(notEmptyNotes);
		}
	}

}// eoc