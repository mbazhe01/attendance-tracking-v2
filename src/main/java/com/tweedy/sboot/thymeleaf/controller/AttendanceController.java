package com.tweedy.sboot.thymeleaf.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tweedy.sboot.thymeleaf.UserMessage;
import com.tweedy.sboot.thymeleaf.entity.AttendanceCode;
import com.tweedy.sboot.thymeleaf.entity.AttendanceMonthly;
import com.tweedy.sboot.thymeleaf.entity.AttendanceMonthlyWrapper;
import com.tweedy.sboot.thymeleaf.entity.AttendanceRecord;
import com.tweedy.sboot.thymeleaf.entity.AttendanceRecordWrapper;
import com.tweedy.sboot.thymeleaf.entity.AttendanceSummaryParamSelector;
import com.tweedy.sboot.thymeleaf.entity.AttendanceYear;
import com.tweedy.sboot.thymeleaf.entity.AttendanceYearsWrap;
import com.tweedy.sboot.thymeleaf.entity.CarryOver;
import com.tweedy.sboot.thymeleaf.entity.CarryOverWrapper;
import com.tweedy.sboot.thymeleaf.entity.Day;
import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.EmployeeShort;
import com.tweedy.sboot.thymeleaf.entity.Location;
import com.tweedy.sboot.thymeleaf.entity.LocationMonthly;
import com.tweedy.sboot.thymeleaf.entity.LocationMonthlyWrapper;
import com.tweedy.sboot.thymeleaf.entity.Month;
import com.tweedy.sboot.thymeleaf.entity.VacationTotals;
import com.tweedy.sboot.thymeleaf.entity.Week;
import com.tweedy.sboot.thymeleaf.service.AttendanceCodeService;
import com.tweedy.sboot.thymeleaf.service.AttendanceRecordService;
import com.tweedy.sboot.thymeleaf.service.AttendanceService;
import com.tweedy.sboot.thymeleaf.service.CalendarService;
import com.tweedy.sboot.thymeleaf.service.EmployeeService;
import com.tweedy.sboot.thymeleaf.service.LocationService;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	private AttendanceService attendanceService;
	private CalendarService calendarService;
	private EmployeeService employeeService;
	private AttendanceRecordService attendanceRecordService;
	private AttendanceCodeService attendanceCodeService;
	private LocationService locationService;

	@Value("${attendancecode.default}")
	int attendanceCodeDefault;

	@Value("${attendancecode.default.weekend}")
	int attendanceCodeDefaultForWeekend;

	@Autowired
	public AttendanceController(@Qualifier("attendanceServiceImpl") AttendanceService theService,
			@Qualifier("calendarServiceImpl") CalendarService theCalendarService,
			@Qualifier("employeeServiceImpl") EmployeeService theEmployeeService,
			@Qualifier("attendanceRecordServiceImpl") AttendanceRecordService theAttendanceRecordService,
			@Qualifier("attendanceCodeServiceImpl") AttendanceCodeService theAttendanceCodeService,
			@Qualifier("locationServiceImpl") LocationService theLocationService) {
		attendanceService = theService;
		calendarService = theCalendarService;
		employeeService = theEmployeeService;
		attendanceRecordService = theAttendanceRecordService;
		attendanceCodeService = theAttendanceCodeService;
		locationService = theLocationService;
	}

	// add mapping for list of available attendance years
	@GetMapping("/years")
	public String getAttendanceYears(Model theModel) {

		List<Integer> years = attendanceService.getAttendanceYears();
		List<AttendanceYear> collect = years.stream().map(x -> new AttendanceYear(x)).collect(Collectors.toList());

		AttendanceYearsWrap yearsWrap = new AttendanceYearsWrap(collect);
		theModel.addAttribute("yearswrap", yearsWrap);
		return "attendance/list-attendance-years";

	}// eof getAttendanceYears()

	// show calendar for the selected year & month
	@GetMapping("/showCalendar")
	public String showCalendar(@RequestParam("month") int theMonth, @RequestParam("year") int theYear, Model theModel)
			throws Exception {

		List<String> header = createCalendarHeader();

		Month monthTable = new Month(theYear, theMonth);
		List<Week> weeks = calendarService.getWeeks(theYear, theMonth);
		monthTable.setWeeks(weeks);

		List<Day> week1 = monthTable.getWeekDays(1);
		List<Day> week2 = monthTable.getWeekDays(2);
		List<Day> week3 = monthTable.getWeekDays(3);
		List<Day> week4 = monthTable.getWeekDays(4);
		List<Day> week5 = monthTable.getWeekDays(5);
		List<Day> week6 = null;
		if (monthTable.getNumberOfWeeks() == 6) {
			week6 = monthTable.getWeekDays(6);
		}

		Day selectedDay = new Day(theYear, theMonth, 1);
		theModel.addAttribute("selecteddate", selectedDay);
		theModel.addAttribute("header", header);
		theModel.addAttribute("week1", week1);
		theModel.addAttribute("week2", week2);
		theModel.addAttribute("week3", week3);
		theModel.addAttribute("week4", week4);
		theModel.addAttribute("week5", week5);
		theModel.addAttribute("week6", week6);
		theModel.addAttribute("usermessage", new UserMessage(null));
		return "attendance/attendance-calendar";
	}

	/**
	 * create weekly calendar header that goes across the webpage
	 * 
	 * @return
	 */
	private List<String> createCalendarHeader() {
		List<String> header = new ArrayList<>();
		header.add("Su");
		header.add("Mo");
		header.add("Tu");
		header.add("We");
		header.add("Th");
		header.add("Fr");
		header.add("Sa");
		return header;
	}

	// show attendance for the selected year & month & day
	@GetMapping("/showDailyAttendance")
	public String showDailyAttendance(@RequestParam("month") int theMonth, @RequestParam("year") int theYear,
			@RequestParam("day") int theDay,

			Model theModel) throws Exception {
		// get attendance records for the selected date

		Boolean hasAttendance = calendarService.hasAttendance(theYear, theMonth, theDay);

		List<AttendanceRecord> records = null;
		Calendar c = Calendar.getInstance();
		c.set(theYear, theMonth - 1, theDay, 0, 0);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int defaultAttend = (Calendar.SUNDAY == dayOfWeek || Calendar.SATURDAY == dayOfWeek)
				? attendanceCodeDefaultForWeekend
				: attendanceCodeDefault;
		String dayOfWeekLabel = (Calendar.SUNDAY == dayOfWeek || Calendar.SATURDAY == dayOfWeek) ? "( weekend )" : "";
		if (hasAttendance) {
			// retrieve attendance records from DB for the date
			records = attendanceRecordService.findAll(theYear, theMonth, theDay);
			// Last name sorter
			Comparator<AttendanceRecord> compareByLastName = (AttendanceRecord o1, AttendanceRecord o2) -> o1.getName()
					.compareTo(o2.getName());
			Collections.sort(records, compareByLastName);

		} else {
			// create new attendance records for the date
			List<Employee> employees = employeeService.findAllActive();
			records = new ArrayList<>();

			Timestamp attendDate = new Timestamp(c.getTimeInMillis());
			Location defaultLocation = locationService.findDefaultLocation();

			for (Employee e : employees) {

				AttendanceRecord attendRec = new AttendanceRecord(attendDate, defaultAttend,
						defaultLocation.getLocationId(), "");
				attendRec.setRecId(0);
				attendRec.setEmployee(e);
				records.add(attendRec);
			} // end for

		}

		// Last name sorter
		Comparator<AttendanceRecord> compareByLastName = (AttendanceRecord o1, AttendanceRecord o2) -> o1.getName()
				.compareTo(o2.getName());
		Collections.sort(records, compareByLastName);

		AttendanceRecordWrapper recordWrapper = new AttendanceRecordWrapper(records);
		List<AttendanceCode> attendanceCodes = attendanceCodeService.findAll();
		List<Location> locations = locationService.findAll();
		theModel.addAttribute("month", theMonth);
		theModel.addAttribute("year", theYear);
		theModel.addAttribute("day", theDay);
		theModel.addAttribute("hasAttendance", hasAttendance);
		theModel.addAttribute("records", recordWrapper);
		theModel.addAttribute("attendcodes", attendanceCodes);
		theModel.addAttribute("locations", locations);
		theModel.addAttribute("dayofweeklabel", dayOfWeekLabel);

		return "attendance/attendance-daily";
	}

	// save attendance records for a single date
	@PostMapping("/saveAttendance")
	public String saveAttendance(@RequestParam("month") int theMonth, @RequestParam("year") int theYear,
			@ModelAttribute AttendanceRecordWrapper attndWrap, Model theModel) throws Exception {

		List<String> header = createCalendarHeader();

		// save attendance records
		for (AttendanceRecord record : attndWrap.getRecords())
			attendanceRecordService.save(record);

		// generate save result message
		UserMessage userMessage = new UserMessage("Sucessufly saved attendance records!");

		Month monthTable = new Month(theYear, theMonth);
		monthTable.setWeeks(calendarService.getWeeks(theYear, theMonth));

		List<Day> week1 = monthTable.getWeekDays(1);
		List<Day> week2 = monthTable.getWeekDays(2);
		List<Day> week3 = monthTable.getWeekDays(3);
		List<Day> week4 = monthTable.getWeekDays(4);
		List<Day> week5 = monthTable.getWeekDays(5);
		List<Day> week6 = null;
		if (monthTable.getNumberOfWeeks() == 6) {
			week6 = monthTable.getWeekDays(6);
		}

		Day selectedDay = new Day(theYear, theMonth, 1);
		theModel.addAttribute("selecteddate", selectedDay);
		theModel.addAttribute("header", header);
		theModel.addAttribute("week1", week1);
		theModel.addAttribute("week2", week2);
		theModel.addAttribute("week3", week3);
		theModel.addAttribute("week4", week4);
		theModel.addAttribute("week5", week5);
		theModel.addAttribute("week6", week6);
		theModel.addAttribute("usermessage", userMessage);

		// after save show the calendar again
		return "attendance/attendance-calendar";
	}

	// delete attendance records for a single date
	@GetMapping("/deleteAttendance")
	public String deleteAttendance(@RequestParam("month") int theMonth, @RequestParam("year") int theYear,
			@RequestParam("day") int theDay, Model theModel) throws Exception {

		attendanceRecordService.deleteAll(theYear, theMonth, theDay);
		List<String> header = createCalendarHeader();
		// generate save result message
		UserMessage userMessage = new UserMessage("Sucessufly removed attendance records!");
		Month monthTable = new Month(theYear, theMonth);
		List<Week> weeks = calendarService.getWeeks(theYear, theMonth);
		monthTable.setWeeks(weeks);

		List<Day> week1 = monthTable.getWeekDays(1);
		List<Day> week2 = monthTable.getWeekDays(2);
		List<Day> week3 = monthTable.getWeekDays(3);
		List<Day> week4 = monthTable.getWeekDays(4);
		List<Day> week5 = monthTable.getWeekDays(5);
		List<Day> week6 = null;
		if (monthTable.getNumberOfWeeks() == 6) {
			week6 = monthTable.getWeekDays(6);
		}

		Day selectedDay = new Day(theYear, theMonth, 1);
		theModel.addAttribute("selecteddate", selectedDay);
		theModel.addAttribute("header", header);
		theModel.addAttribute("week1", week1);
		theModel.addAttribute("week2", week2);
		theModel.addAttribute("week3", week3);
		theModel.addAttribute("week4", week4);
		theModel.addAttribute("week5", week5);
		theModel.addAttribute("week6", week6);
		theModel.addAttribute("usermessage", userMessage);

		// after deleting show the calendar again
		return "attendance/attendance-calendar";
	}

	@GetMapping("/selectReport")
	public String selectReport(Model theModel) {
		return "reports/report-selector";
	}

	// show attendance summary report parms selector
	@GetMapping("/attendanceSummary")
	public String attendanceSummary(Model theModel) {

		List<Integer> years = attendanceService.getAttendanceYears();
		List<AttendanceYear> attendYears = years.stream().map(x -> new AttendanceYear(x)).collect(Collectors.toList());

		List<Employee> employees = employeeService.findAll();
		List<EmployeeShort> employeesShort = employees.stream()
				.map(x -> new EmployeeShort(x.getEmplId(), x.getLastName() + ", " + x.getFirstName()))
				.collect(Collectors.toList());
		// Last name sorter
		Comparator<EmployeeShort> compareByLastName = (EmployeeShort o1, EmployeeShort o2) -> o1.getName()
				.compareTo(o2.getName());
		Collections.sort(employeesShort, compareByLastName);

		theModel.addAttribute("attendyears", attendYears);
		// create empty param selector
		Integer latestYear = findMax(years);
		AttendanceSummaryParamSelector parm = new AttendanceSummaryParamSelector(latestYear, 0);
		theModel.addAttribute("parm", parm);

		theModel.addAttribute("employees", employeesShort);

		return "reports/attendance-summary-parms";
	}

	// show attendance summary report parms selector
	@PostMapping("/attendanceSummaryReport")
	public String attendanceSummaryReport(@ModelAttribute AttendanceSummaryParamSelector param, Model theModel) {

		Integer year = param.getYear();
		Integer emplId = param.getEmplId();
		Employee employee = employeeService.findById(emplId);

		VacationTotals vacTotals = attendanceService.getVacationTotals(year, emplId);
		List<AttendanceMonthly> attend = attendanceService.getAttendanceMonthly(year, emplId);
		AttendanceMonthlyWrapper attendWrap = new AttendanceMonthlyWrapper(attend);

		List<CarryOver> vacCarryOvers = attendanceService.getVacationCarryOverDays(year, emplId);

		CarryOverWrapper carryovers = new CarryOverWrapper(vacCarryOvers);

		Double totalCarryOverDays = attendanceService.getTotalCarryOverDays(vacCarryOvers);

		theModel.addAttribute("employee", employee);
		theModel.addAttribute("year", year);
		theModel.addAttribute("vactotals", vacTotals);
		theModel.addAttribute("attendwrap", attendWrap);
		vacCarryOvers.forEach(s -> s.setZeroCarryOverDaysToNull());
		theModel.addAttribute("carryoverwrap", carryovers);
		theModel.addAttribute("totalcarryovers", totalCarryOverDays);
		return "reports/attendance-summary-report";
	}

	// show location summary report parms selector
	@GetMapping("/locationSummary")
	public String locationSummary(Model theModel) {
		List<Integer> years = attendanceService.getAttendanceYears();
		List<AttendanceYear> attendYears = years.stream().map(x -> new AttendanceYear(x)).collect(Collectors.toList());

		List<Employee> employees = employeeService.findAll();
		List<EmployeeShort> employeesShort = employees.stream()
				.map(x -> new EmployeeShort(x.getEmplId(), x.getLastName() + ", " + x.getFirstName()))
				.collect(Collectors.toList());
		// Last name sorter
		Comparator<EmployeeShort> compareByLastName = (EmployeeShort o1, EmployeeShort o2) -> o1.getName()
				.compareTo(o2.getName());
		Collections.sort(employeesShort, compareByLastName);

		theModel.addAttribute("attendyears", attendYears);
		// create empty param selector
		Integer latestYear = findMax(years);
		AttendanceSummaryParamSelector parm = new AttendanceSummaryParamSelector(latestYear, 0);
		theModel.addAttribute("parm", parm);

		theModel.addAttribute("employees", employeesShort);

		return "reports/location-summary-parms";
	}

	// show attendance summary report params selector
	@PostMapping("/locationSummaryReport")
	public String locationSummaryReport(@ModelAttribute AttendanceSummaryParamSelector param, Model theModel) {

		Integer year = param.getYear();
		Integer emplId = param.getEmplId();
		Employee employee = employeeService.findById(emplId);

		List<LocationMonthly> locm = attendanceService.getLocationMonthly(year, emplId);
		LocationMonthlyWrapper locmWrap = new LocationMonthlyWrapper(locm);

		theModel.addAttribute("employee", employee);
		theModel.addAttribute("year", year);
		theModel.addAttribute("locmwrap", locmWrap);

		return "reports/location-summary-report";
	}

	// function return maximum value in an unsorted
	// list in Java using Collection
	public Integer findMax(List<Integer> list) {

		// check list is empty or not
		if (list == null || list.size() == 0) {
			return Integer.MIN_VALUE;
		}

		// create a new list to avoid modification
		// in the original list
		List<Integer> sortedlist = new ArrayList<>(list);

		// sort list in natural order
		Collections.sort(sortedlist);

		// last element in the sorted list would be maximum
		return sortedlist.get(sortedlist.size() - 1);
	}

}// eof controller
