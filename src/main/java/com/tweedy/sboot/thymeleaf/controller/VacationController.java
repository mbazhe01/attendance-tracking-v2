package com.tweedy.sboot.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.VacationTL;
import com.tweedy.sboot.thymeleaf.entity.VacationsWrapper;
import com.tweedy.sboot.thymeleaf.service.EmployeeService;
import com.tweedy.sboot.thymeleaf.service.GenericService;
import com.tweedy.sboot.thymeleaf.service.VacationService;

@Controller
@RequestMapping("/vacations")
public class VacationController {
	private GenericService genericService;
	private EmployeeService employeeService;

	@Value("${vacation.personalDaysDefault}")
	int personalDaysDefault;

	@Autowired
	VacationService vacService;

	@Autowired
	public VacationController(@Qualifier("employeeServiceImpl") EmployeeService theEmployeeService,
			@Qualifier("genericServiceImpl") GenericService theGenericService) {
		employeeService = theEmployeeService;
		genericService = theGenericService;
	}

	// update employee vacations
	@GetMapping("/showFormForVacationUpdate")
	public String showFormForVacationUpdate(@RequestParam("employeeId") String theId, Model theModel)
			throws NumberFormatException, Exception {

		Employee theEmployee = employeeService.findById(Integer.parseInt(theId));

		/**
		 * //@SuppressWarnings("unchecked") //List<Vacation> vacations =
		 * (List<Vacation>) genericService.findAll(Integer.parseInt(theId), //
		 * "Vacation");
		 * 
		 */
		List<VacationTL> vacations = vacService.findAllByEmployeeId(Integer.parseInt(theId));

		VacationsWrapper vacationsWrap = new VacationsWrapper(vacations);

		theModel.addAttribute("employee", theEmployee);
		theModel.addAttribute("vacationwrap", vacationsWrap);
		theModel.addAttribute("usermessage", new UserMessage(null));

		return "vacations/employee-form-vacation";

	}// end of showFormForVacationUpdate

	// add employee vacation
	@GetMapping("/addVacation")
	public String addVacation(@RequestParam("employeeId") String theId, Model theModel) throws Exception {

		Employee empl = employeeService.findById(Integer.parseInt(theId));
		// EmployeeTL employee = empl.convertToEmployeeTL();
		// get employee emails
		@SuppressWarnings("unchecked")
		// List<Vacation> vacations = (List<Vacation>)
		// genericService.findAll(Integer.parseInt(theId), "Vacation");
		List<VacationTL> vacations = vacService.findAllByEmployeeId(Integer.parseInt(theId));

		VacationsWrapper vacationsWrap = new VacationsWrapper(vacations);

		// add one empty vacation year
		VacationTL vacation = new VacationTL();
		vacation.setEmplId(empl.getEmplId());
		vacation.setPersonalDays(personalDaysDefault);
		vacations.add(vacation);

		theModel.addAttribute("employee", empl);
		theModel.addAttribute("vacationwrap", vacationsWrap);
		theModel.addAttribute("usermessage", new UserMessage(null));
		return "vacations/employee-form-vacation";

	}// eof addVacation

	// save employee vacation allocation
	@PostMapping("/saveVacation")
	public String saveVacation(@ModelAttribute VacationsWrapper vacationsWrap, Model theModel) throws Exception {

		// save vacations
		for (VacationTL v : vacationsWrap.getVacations()) {
			genericService.save(v.toVacation());
		}

		Employee empl = employeeService.findById(vacationsWrap.getEmployeeId());

		@SuppressWarnings("unchecked")
		List<VacationTL> vacationsAfterSave = vacService.findAllByEmployeeId(empl.getEmplId());
		VacationsWrapper vacationWrap = new VacationsWrapper(vacationsAfterSave);

		theModel.addAttribute("employee", empl);
		theModel.addAttribute("usermessage", new UserMessage("Successufly saved vacations!"));
		theModel.addAttribute("vacationwrap", vacationWrap);

		// show employee vacation allocation form with save result message on the page
		return "vacations/employee-form-vacation";
	}// eof saveVacation

	// save employee vacation allocation with duplicates validation
	@PostMapping("/saveVacationWithValidation")
	public String saveVacationWithValidation(@ModelAttribute VacationsWrapper vacationsWrap, Model theModel)
			throws Exception {
		Employee empl = employeeService.findById(vacationsWrap.getEmployeeId());
		UserMessage userMessage = null;

		if (foundDuplicatVacationYear(vacationsWrap.getVacations())) {
			// show error message, return vacationWrap without change
			theModel.addAttribute("vacationwrap", vacationsWrap);
			userMessage = new UserMessage("Duplicate years in vacation days allocation!");
			userMessage.setIsErrorMessage(true);
		} else {
			// save vacations
			for (VacationTL v : vacationsWrap.getVacations())
				genericService.save(v.toVacation());

			@SuppressWarnings("unchecked")
			List<VacationTL> vacationsAfterSave = vacService.findAllByEmployeeId(empl.getEmplId());
			VacationsWrapper vacationWrap = new VacationsWrapper(vacationsAfterSave);
			theModel.addAttribute("vacationwrap", vacationWrap);
			userMessage = new UserMessage("Successufly saved vacations!");
			userMessage.setIsErrorMessage(false);
		}

		theModel.addAttribute("employee", empl);
		theModel.addAttribute("usermessage", userMessage);

		// show employee vacation allocation form with save result message on the page
		return "vacations/employee-form-vacation";
	}

	/**
	 * find duplicate vacation years
	 * 
	 * @param vacations
	 * @return
	 */
	private boolean foundDuplicatVacationYear(List<VacationTL> vacations) {
		Optional<VacationTL> dupsFound;

		List<VacationTL> tmpVacation = new ArrayList<>();
		if (vacations != null && !vacations.isEmpty()) {
			for (VacationTL v : vacations) {
				dupsFound = tmpVacation.stream().filter(c -> c.getVacationYear() == (v.getVacationYear())).findAny();
				if (dupsFound.isPresent()) {
					// found duplicate vacation object
					return true;
				} else {
					tmpVacation.add(v);
				}
			}
		}

		return false;
	}

	// delete vacation
	@GetMapping("/deleteVacation")
	public String deleteVacation(@RequestParam("vacationId") int theId, Model theModel) throws Exception {

		int employeeId = genericService.getEmployeeByEntityId(theId, "Vacation", "vacationId");
		genericService.delete(theId, "Vacation", "vacationId");

		// generate save result message
		UserMessage userMessage = new UserMessage("Successufly deleted vacation allocation!");

		VacationsWrapper vacationWrap = new VacationsWrapper(
				(List<VacationTL>) vacService.findAllByEmployeeId(employeeId));
		Employee empl = employeeService.findById(employeeId);

		theModel.addAttribute("employee", empl);
		theModel.addAttribute("usermessage", userMessage);
		theModel.addAttribute("vacationwrap", vacationWrap);

		return "/vacations/employee-form-vacation";

	}// eof deleteVacation()
}// eoc
