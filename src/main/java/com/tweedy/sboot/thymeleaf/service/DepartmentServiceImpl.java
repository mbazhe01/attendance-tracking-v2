package com.tweedy.sboot.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tweedy.sboot.thymeleaf.dao.DepartmentDAO;
import com.tweedy.sboot.thymeleaf.entity.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	// need to inject location dao
	@Autowired
	private DepartmentDAO departmentDAO;
			
	@Autowired
	public DepartmentServiceImpl(@Qualifier("departmentDAOImpl") DepartmentDAO theDepartmentDAO) {
		departmentDAO = theDepartmentDAO;
	}
	
	@Override
	@Transactional
	public List<Department> findAll() {
		return departmentDAO.getDepartments();
	}

	@Override
	@Transactional
	public Department findById(int theId) {
		// TODO Auto-generated method stub
		return departmentDAO.getDepartment(theId);
	}

		@Override
		@Transactional
		public void save(Department theDepartment) {
			departmentDAO.saveDepartment(theDepartment);

		}

		@Override
		@Transactional
		public void deleteById(int theId) {
			
			departmentDAO.deleteDepartment(theId);
		}

}
