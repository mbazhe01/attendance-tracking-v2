package com.tweedy.sboot.thymeleaf.dao;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.EmployeeDev;
import com.tweedy.sboot.thymeleaf.entity.NoteDev;
import com.tweedy.sboot.thymeleaf.entity.Phone;

public interface EmployeeDAO {

	public List<Employee> getEmployees();
	
	public List<EmployeeDev> getEmployeesDev();
	
	public List<Employee> getEmployeesWithAttendance(Date attendanceDate);
	
	public List<Employee> getActiveEmployees();

	public void saveEmployee(Employee theEmployee);

	public Employee getEmployee(int theId);
	
	public EmployeeDev getEmployeeById(int theId);

	public void deleteEmployee(int theId);
	
	public void deleteEmployeeById(int theId);
	
	public void saveEmployee(EmployeeDev theEmployee) throws Exception;

	public void deleteEmployee(EmployeeDev employee);

	public List<Phone> getPhonesByEmployee(@Valid EmployeeDev employee);

	public void deletePhone(Phone p);

	public List<Address> getAddressesByEmployee(@Valid EmployeeDev employee);

	public void deleteAddress(Address a);

	public List<NoteDev> getNotesByEmployee(@Valid EmployeeDev employee);

	public void deleteNote(NoteDev n);
}
