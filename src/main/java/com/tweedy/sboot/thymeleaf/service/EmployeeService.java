package com.tweedy.sboot.thymeleaf.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.EmployeeDev;
import com.tweedy.sboot.thymeleaf.entity.NoteDev;
import com.tweedy.sboot.thymeleaf.entity.Phone;



public interface EmployeeService {

	public List<Employee> findAll();
	
	public List<EmployeeDev> findAllEmployeeDev();
	
	public List<Employee> findAllWithAttendance(Date date);
	
	public List<Employee> findAllActive();
	
	public Employee findById(int theId);
	
	public EmployeeDev findEmployeeById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
	public void deleteEmployeeById(int theId);

	public void save(@Valid EmployeeDev employee) throws Exception;

	public void deleteEmployee(EmployeeDev employee);

	public List<Phone> getPhonesByEmplId(int emplId);

	public List<Phone> getPhonesByEmployee(@Valid EmployeeDev employee);
	
	public List<Phone> notInListPhones(List<Phone> list1, List<Phone> list2);
	
	public void deletePhones(List<Phone> phones);

	public List<Address> getAddressesByEmployee(@Valid EmployeeDev employee);

	public List<Address> notInListAddresses(List<Address> origAddrs, List<Address> curAddrs);

	public void deleteAddresses(List<Address> addrsToDelete);

	public List<NoteDev> notInListNotes(List<NoteDev> origNotes, List<NoteDev> curNotes);

	public List<NoteDev> getNotesByEmployee(@Valid EmployeeDev employee);

	public void deleteNotes(List<NoteDev> notesToDelete);
}
