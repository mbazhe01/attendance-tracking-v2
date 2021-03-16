package com.tweedy.sboot.thymeleaf.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Department;
import com.tweedy.sboot.thymeleaf.entity.Location;


public interface DepartmentDAO {

	public List<Department> getDepartments();

	public void saveDepartment(Department theDepartment);

	public Department getDepartment(int theId);

	public void deleteDepartment(int theId);
}
