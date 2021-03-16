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
@Table(name = "tb_department")
public class Department {

	private int departmentId;
	@NotNull(message=" is required.")
	@Size(min=2, message="Department name should be minimum 2 characters.")
    private String departmentName;
    private String description;
    
    public Department() {
    	
    }

	public Department(int departmentId, String departmentName, String description) {
	
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="department_id")
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "department_name", nullable = false)
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "description", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", description="
				+ description + "]";
	}
    
    
}
