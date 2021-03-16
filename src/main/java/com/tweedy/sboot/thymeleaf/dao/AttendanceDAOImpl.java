package com.tweedy.sboot.thymeleaf.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.tweedy.sboot.thymeleaf.entity.AttendanceMonthly;
import com.tweedy.sboot.thymeleaf.entity.CarryOver;
import com.tweedy.sboot.thymeleaf.entity.LocationMonthly;
import com.tweedy.sboot.thymeleaf.entity.VacationTotals;

@Repository
public class AttendanceDAOImpl implements AttendanceDAO {
	@Value("${sp.GetAttendanceYears}")
	String spGetAttendanceYears;

	@Value("${sp.EmployeeVacationTotals}")
	String spVacationTotals;

	@Value("${sp.EmployeeAttendanceByMonth}")
	String spAttendanceMonthly;

	@Value("${sp.CarryOverDays}")
	String spCarryOverDays;

	@Value("${sp.EmployeeLocationByMonth}")
	String spLocationMonthly;

	// define field for entity manager
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public AttendanceDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getAttendanceYears() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(spGetAttendanceYears);

		// Call the stored procedure.

		List<Integer> results = storedProcedure.getResultList();

		// return the results
		return results;
	}// eof getAttendanceYears()

	@Override
	public VacationTotals getVacationTotals(Integer year, Integer emplId) {

		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(spVacationTotals);
		storedProcedure.registerStoredProcedureParameter("empl_id", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("vacYear", Integer.class, ParameterMode.IN);

		storedProcedure.setParameter("empl_id", emplId);
		storedProcedure.setParameter("vacYear", year);

		// Call the stored procedure.
		List<Object[]> results = storedProcedure.getResultList();
		VacationTotals vacTotals = new VacationTotals();
		// mapping

		results.stream().forEach((record) -> {
			vacTotals.setLastName((String) record[0]);
			vacTotals.setFirstName((String) record[1]);
			vacTotals.setVacationYear((Integer) record[2]);
			vacTotals.setVacationDaysAllowed((Integer) record[3]);
			vacTotals.setPersonalDaysAllowed((Integer) record[4]);
			vacTotals.setPersonalDaysTaken((Integer) record[5]);
			BigDecimal vacDaysTaken = (BigDecimal) record[6];
			vacTotals.setVacationDaysTaken(vacDaysTaken.doubleValue());
			vacTotals.setVacationDaysLeft(((BigDecimal) record[7]).doubleValue());
			vacTotals.setPersonalDaysLeft((Integer) record[8]);

			vacTotals.setUsedCarryOverDays(((Double) record[10]).doubleValue());
			vacTotals.setCarryOverDays(((Double) record[11]).doubleValue());
			vacTotals.setCarryOverDaysLeft(((Double) record[12]).doubleValue());
			vacTotals.setVacAndCarryOverDaysAllowed(((Double) record[13]).doubleValue());
			vacTotals.setVacAndCarryOverDaysLeft(((Double) record[14]).doubleValue());
		});

		// return the results
		return vacTotals;
	}

	@Override
	public List<AttendanceMonthly> getAttendanceMonthly(Integer year, Integer empId) {

		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(spAttendanceMonthly);
		storedProcedure.registerStoredProcedureParameter("empl_id", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("year", Integer.class, ParameterMode.IN);

		storedProcedure.setParameter("empl_id", empId);
		storedProcedure.setParameter("year", year);

		// Call the stored procedure.
		List<Object[]> results = storedProcedure.getResultList();

		List<AttendanceMonthly> attendMonthly = new ArrayList<>();
		// mapping results

		results.stream().forEach((record) -> {
			AttendanceMonthly attend = new AttendanceMonthly();
			attend.setAttendanceCode((String) record[0]);
			attend.setJanDays((Integer) record[1]);
			attend.setFebDays((Integer) record[2]);
			attend.setMarDays((Integer) record[3]);
			attend.setAprDays((Integer) record[4]);
			attend.setMayDays((Integer) record[5]);
			attend.setJunDays((Integer) record[6]);
			attend.setJulDays((Integer) record[7]);
			attend.setAugDays((Integer) record[8]);
			attend.setSepDays((Integer) record[9]);
			attend.setOctDays((Integer) record[10]);
			attend.setNovDays((Integer) record[11]);
			attend.setDecDays((Integer) record[12]);
			attend.setTotal((Integer) record[13]);
			attendMonthly.add(attend);
			attend.setAttendanceCodeDescription((String) record[14]);
		});

		// return the results
		return attendMonthly;

	}// eof

	@Override
	public List<CarryOver> getCarryOverDays(Integer year, int emplId) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(spCarryOverDays);
		storedProcedure.registerStoredProcedureParameter("empl_id", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("empl_id", emplId);
		storedProcedure.registerStoredProcedureParameter("year", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("year", year);

		// Call the stored procedure.
		List<Object[]> results = storedProcedure.getResultList();

		List<CarryOver> carryOvers = new ArrayList<>();
		// mapping results
		results.stream().forEach((record) -> {
			CarryOver co = new CarryOver();
			co.setVacYear((Integer) record[0]);
			co.setVacDaysAllowed((Integer) record[1]);
			co.setVacDaysTaken((Double) record[2]);
			co.setCarryOverDays((Double) record[3]);
			co.setCarryOverExpired((Integer) record[4]);
			co.setUsedCarryOverDays((Double) record[5]);
			
			carryOvers.add(co);
		});

		return carryOvers;
	}

	@Override
	public List<LocationMonthly> getLocationMonthly(Integer year, Integer empId) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(spLocationMonthly);
		storedProcedure.registerStoredProcedureParameter("empl_id", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("year", Integer.class, ParameterMode.IN);

		storedProcedure.setParameter("empl_id", empId);
		storedProcedure.setParameter("year", year);

		// Call the stored procedure.
		List<Object[]> results = storedProcedure.getResultList();

		List<LocationMonthly> locationMonthly = new ArrayList<>();
		// mapping results

		results.stream().forEach((record) -> {
			LocationMonthly locm = new LocationMonthly();
			locm.setLocationName((String) record[0]);
			locm.setJanDays((Integer) record[1]);
			locm.setFebDays((Integer) record[2]);
			locm.setMarDays((Integer) record[3]);
			locm.setAprDays((Integer) record[4]);
			locm.setMayDays((Integer) record[5]);
			locm.setJunDays((Integer) record[6]);
			locm.setJulDays((Integer) record[7]);
			locm.setAugDays((Integer) record[8]);
			locm.setSepDays((Integer) record[9]);
			locm.setOctDays((Integer) record[10]);
			locm.setNovDays((Integer) record[11]);
			locm.setDecDays((Integer) record[12]);
			locm.setTotalByLocation((Integer) record[13]);
			locationMonthly.add(locm);
		});

		// return the results
		return locationMonthly;
	}

}
