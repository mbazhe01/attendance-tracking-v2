package com.tweedy.sboot.thymeleaf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tweedy.sboot.thymeleaf.dao.EmployeeDAO;
import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.EmployeeDev;
import com.tweedy.sboot.thymeleaf.entity.NoteDev;
import com.tweedy.sboot.thymeleaf.entity.Phone;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// need to inject customer dao
	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	public EmployeeServiceImpl(@Qualifier("employeeDAOImpl") EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDAO.getEmployees();
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		employeeDAO.saveEmployee(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeDAO.deleteEmployee(theId);
	}

	@Override
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployee(theId);
	}

	@Override
	public List<Employee> findAllWithAttendance(Date date) {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployeesWithAttendance(date);
	}

	@Override
	public List<Employee> findAllActive() {
		// TODO Auto-generated method stub
		return employeeDAO.getActiveEmployees();
	}

	@Override
	public void save(EmployeeDev employee) throws Exception {
		// re-attach phones because Thymeleaf losses the reference to employrr
		if (employee.getPhones() != null)
			for (Phone phone : employee.getPhones()) {
				phone.setEmployee(employee);
			}

		// re-attach addresses
		if (employee.getAddresses() != null)
			for (Address addr : employee.getAddresses())
				addr.setEmployee(employee);

		// re-attach addresses
		if (employee.getNotes() != null)
			for (NoteDev note : employee.getNotes())
				note.setEmployee(employee);

		employeeDAO.saveEmployee(employee);

	}

	@Override
	public List<EmployeeDev> findAllEmployeeDev() {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployeesDev();
	}

	@Override
	public EmployeeDev findEmployeeById(int theId) {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployeeById(theId);
	}

	@Override
	@Transactional
	public void deleteEmployeeById(int theId) {
		employeeDAO.deleteEmployeeById(theId);

	}

	@Override
	@Transactional
	public void deleteEmployee(EmployeeDev employee) {
		employeeDAO.deleteEmployee(employee);

	}

	@Override
	public List<Phone> getPhonesByEmployee(@Valid EmployeeDev employee) {
		// TODO Auto-generated method stub
		return employeeDAO.getPhonesByEmployee(employee);
	}

	@Override
	public List<Phone> getPhonesByEmplId(int emplId) {
		// TODO Auto-generated method stub
		return null;
	}

	// utility function
	@Override
	public List<Phone> notInListPhones(List<Phone> list1, List<Phone> list2) {

		List<Phone> notList = new ArrayList<>();

		if (list1 == null && list2 == null)
			return notList;
		else if (list2 == null) {
			return list1;
		} else if (list1 == null) {
			return notList;
		} else {
			for (Phone p1 : list1) {
				boolean found = false;
				for (Phone p2 : list2)
					if (p1.getPhoneId() == p2.getPhoneId())
						found = true;
				if (!found)
					notList.add(p1);
			}

			return notList;
		}

	}// eof

	@Override
	public void deletePhones(List<Phone> phones) {
		if(phones!=null)
			for(Phone p : phones)
				employeeDAO.deletePhone(p);
		
	}

	@Override
	public List<Address> getAddressesByEmployee(@Valid EmployeeDev employee) {
		// TODO Auto-generated method stub
		return employeeDAO.getAddressesByEmployee(employee);
	}

	@Override
	public List<Address> notInListAddresses(List<Address> list1, List<Address> list2) {
		List<Address> notList = new ArrayList<>();

		if (list1 == null && list2 == null)
			return notList;
		else if (list2 == null) {
			return list1;
		} else if (list1 == null) {
			return notList;
		} else {
			for (Address p1 : list1) {
				boolean found = false;
				for (Address p2 : list2)
					if (p1.getAddrId() == p2.getAddrId())
						found = true;
				if (!found)
					notList.add(p1);
			}

			return notList;
		}
	}//eof

	@Override
	public void deleteAddresses(List<Address> addrs) {
		if(addrs!=null)
			for(Address a : addrs)
				employeeDAO.deleteAddress(a);
		
	}

	@Override
	public List<NoteDev> notInListNotes(List<NoteDev> list1, List<NoteDev> list2) {
		List<NoteDev> notList = new ArrayList<>();

		if (list1 == null && list2 == null)
			return notList;
		else if (list2 == null) {
			return list1;
		} else if (list1 == null) {
			return notList;
		} else {
			for (NoteDev p1 : list1) {
				boolean found = false;
				for (NoteDev p2 : list2)
					if (p1.getNoteId() == p2.getNoteId())
						found = true;
				if (!found)
					notList.add(p1);
			}

			return notList;
		}
	}

	@Override
	public List<NoteDev> getNotesByEmployee(@Valid EmployeeDev employee) {
		return employeeDAO.getNotesByEmployee(employee);
	}

	@Override
	public void deleteNotes(List<NoteDev> notes) {
		// TODO Auto-generated method stub
		if(notes!=null)
			for(NoteDev n : notes)
				employeeDAO.deleteNote(n);
	}
	
}
