package com.tweedy.sboot.thymeleaf.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.EmployeeDev;
import com.tweedy.sboot.thymeleaf.entity.NoteDev;
import com.tweedy.sboot.thymeleaf.entity.Phone;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	// define field for entity manager
	private EntityManager entityManager;

	@Autowired
	EmployeeRepository emplRepo;
	
	@Autowired
	PhoneRepository phoneRepo;
	
	@Autowired
	AddressRepository addrRepo;
	
	@Autowired 
	NoteRepository noteRepo;
	
	// set up constructor injection
	@Autowired
	public EmployeeDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> getEmployees() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query ... sort by employee id
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);

		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();

		// return the results
		return employees;

	}

	@Override
	public void saveEmployee(Employee theEmployee) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// adjust Date data type

		if (theEmployee.getStartDate().equals(""))
			theEmployee.setStartDate(null);

		if (theEmployee.getResignDate().equals(""))
			theEmployee.setResignDate(null);

		// theEmployee.setStartDate(null);
		// theEmployee.setResignDate(null);

		// save/update the employee ... finally LOL
		currentSession.saveOrUpdate(theEmployee);
	}
	
	@Override
	public void saveEmployee(EmployeeDev theEmployee) throws Exception {
		try {		
		emplRepo.save(theEmployee);
		} catch(Exception ex) {
			throw ex;
		}
	}
	

	@Override
	public Employee getEmployee(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using the primary key
		Employee theEmployee = currentSession.get(Employee.class, theId);

		return theEmployee;
	}

	@Override
	public void deleteEmployee(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Employee where emplId=:employeeId");
		theQuery.setParameter("employeeId", theId);

		theQuery.executeUpdate();

	}

	@Override
	public List<Employee> getEmployeesWithAttendance(Date attendanceDate) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query ... sort by employee id
		Query<Employee> theQuery = currentSession.createQuery("from Employee e "
				+ " where exists ( from AttendanceRecord r" + " where e.emplId = r.emplId and r.recDate := recdate)",
				Employee.class);

		theQuery.setParameter("recDate", attendanceDate);

		// execute query and get result list of
		// employees with the attendance records for the date
		List<Employee> employees = theQuery.getResultList();

		// return the results
		return employees;
	}

	@Override
	public List<Employee> getActiveEmployees() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query ... sort by employee id
		Query<Employee> theQuery = currentSession.createQuery("from Employee e " + " where activeStatus = 1",
				Employee.class);
		// execute query and get result list of
		// employees with the attendance records for the date
		List<Employee> employees = theQuery.getResultList();

		// return the results
		return employees;
	}

	

	@Override
	public List<EmployeeDev> getEmployeesDev() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query ... sort by employee id
		Query<EmployeeDev> theQuery = currentSession.createQuery("from EmployeeDev", EmployeeDev.class);

		// execute query and get result list
		List<EmployeeDev> employees = theQuery.getResultList();

		// return the results
		return employees;
	}

	@Override
	public EmployeeDev getEmployeeById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using the primary key
		EmployeeDev theEmployee = currentSession.get(EmployeeDev.class, theId);

		return theEmployee;
	}

	@Override
	public void deleteEmployeeById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from EmployeeDev where emplId=:employeeId");
		theQuery.setParameter("employeeId", theId);

		theQuery.executeUpdate();

	}

	@Override
	public void deleteEmployee(EmployeeDev employee) {
		
		emplRepo.delete(employee);
		
	}



	@Override
	public List<Phone> getPhonesByEmployee(@Valid EmployeeDev employee) {
		// TODO Auto-generated method stub
		return phoneRepo.findAllPhonesByEmployee(employee);
	}



	@Override
	public void deletePhone(Phone p) {
		
		phoneRepo.delete(p);
		
	}

	@Override
	public List<Address> getAddressesByEmployee(@Valid EmployeeDev employee) {
		// TODO Auto-generated method stub
		return addrRepo.findAllAddressesByEmployee(employee);
	}

	@Override
	public void deleteAddress(Address a) {
		
		addrRepo.delete(a);
		
	}

	@Override
	public List<NoteDev> getNotesByEmployee(@Valid EmployeeDev employee) {
		// TODO Auto-generated method stub
		return noteRepo.findAllNotesByEmployee(employee);
	}

	@Override
	public void deleteNote(NoteDev n) {
		// TODO Auto-generated method stub
		noteRepo.delete(n);
	}

}
