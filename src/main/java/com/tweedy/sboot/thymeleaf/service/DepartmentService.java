package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import com.tweedy.sboot.thymeleaf.entity.Department;

public interface DepartmentService {

	public List<Department> findAll();
	
	public Department findById(int theId);
	
	public void save(Department theDepartment);
	
	public void deleteById(int theId);
	
}
