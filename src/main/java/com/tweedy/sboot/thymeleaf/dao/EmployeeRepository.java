package com.tweedy.sboot.thymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.EmployeeDev;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDev, Integer> {

	// that's it ... no need to write any code LOL!
	
}
