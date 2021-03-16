package com.tweedy.sboot.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tweedy.sboot.thymeleaf.UserMessage;
import com.tweedy.sboot.thymeleaf.dao.DuplicateYear;
import com.tweedy.sboot.thymeleaf.entity.CarryoverRecord;
import com.tweedy.sboot.thymeleaf.entity.CarryoverRecordsWrapper;
import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.service.CarryoverService;
import com.tweedy.sboot.thymeleaf.service.EmployeeService;
import com.tweedy.sboot.thymeleaf.service.GenericService;

@Controller
@RequestMapping("/carryovers")
public class CarryoverController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	CarryoverService carryoverSrv;
	
	@Autowired
	GenericService genericService;

	@GetMapping("/showFormForCarryoverUpdate")
	public String showFormForVacationUpdate(@RequestParam("employeeId") String theId, Model theModel)
			throws NumberFormatException, Exception {

		Employee theEmployee = employeeService.findById(Integer.parseInt(theId));

		//List<CarryoverRecord> carryOvers = carryoverSrv.findAllByEmplId(theEmployee.getEmplId());
		List<CarryoverRecord> carryOvers = carryoverSrv.findAllByEmplIdOrderByVacationYearDesc(theEmployee.getEmplId());

		CarryoverRecordsWrapper carryOversWrap = new CarryoverRecordsWrapper(carryOvers);

		theModel.addAttribute("employee", theEmployee);
		theModel.addAttribute("carryoversWrap", carryOversWrap);
		theModel.addAttribute("usermessage", new UserMessage(null));

		return "carryovers/employee-form-carryover";
	}// eof

	@GetMapping("/addCarryOver")
	public String addCarryoverForTheYear(@RequestParam("employeeId") String theId, Model theModel)
			throws NumberFormatException, Exception {

		Employee empl = employeeService.findById(Integer.parseInt(theId));

		//List<CarryoverRecord> carryOvers = carryoverSrv.findAllByEmplId(empl.getEmplId());
		List<CarryoverRecord> carryOvers = carryoverSrv.findAllByEmplIdOrderByVacationYearDesc(empl.getEmplId());

		CarryoverRecordsWrapper carryOversWrap = new CarryoverRecordsWrapper(carryOvers);

		carryOversWrap.addCarryoverRecord(new CarryoverRecord(empl.getEmplId()));

		theModel.addAttribute("employee", empl);
		theModel.addAttribute("carryoversWrap", carryOversWrap);
		theModel.addAttribute("usermessage", new UserMessage(null));

		return "carryovers/employee-form-carryover";
	}// eof

	// delete carry over
	@GetMapping("/deleteCarryOver")
	public String deleteCarryOver(@RequestParam("id") int theId, CarryoverRecordsWrapper carryoversWrap, Model theModel) throws Exception {

		//int employeeId = genericService.getEmployeeByEntityId(theId, "Vacation", "vacationId");
		//genericService.delete(theId, "Vacation", "vacationId");

		Employee empl = employeeService.findById(theId);

		
		// generate save result message
		UserMessage userMessage = new UserMessage("Successufly deleted vacation allocation!");

		//VacationsWrapper vacationWrap = new VacationsWrapper(
		//		(List<VacationTL>) vacService.findAllByEmployeeId(employeeId));
		//Employee empl = employeeService.findById(employeeId);

		theModel.addAttribute("employee", empl);
		theModel.addAttribute("usermessage", userMessage);
		theModel.addAttribute("carryoversWrap", carryoversWrap);

		return "carryovers/employee-form-carryover";

	}// eof deleteCarryOver()

	// save employee carry over allocation
	@PostMapping("/saveCarryOver")
	public String saveCarryOver(CarryoverRecordsWrapper carryoversWrap, BindingResult bindingResult,
			Model theModel,
			@RequestParam(required = false, value = "deletecarryover") String deleteCarryOver) throws Exception {

		Employee empl = employeeService.findById(carryoversWrap.getEmployeeId());
		UserMessage userMessage = null;

		DuplicateYear dYear = foundDuplicatCarryoverYear(carryoversWrap.getCarryovers());

		if (deleteCarryOver != null) {
			int carryoverId = Integer.parseInt(deleteCarryOver);
			int removedYear = carryoversWrap.deleteCarryOver(carryoverId);
			userMessage = new UserMessage("Removed carry over allocation for  " + removedYear + " from the list. You still have to click Save button to commit.");
			theModel.addAttribute("carryoversWrap", carryoversWrap);
		}
		
		// check if carry over record exist for this employee and year
		else if (dYear.isFound()) {
			// show error message, return carryoversWrap without change
			theModel.addAttribute("carryoversWrap", carryoversWrap);
			userMessage = new UserMessage("Duplicate year " + dYear.getDupYear() + " in carry over days allocation!");
			userMessage.setIsErrorMessage(true);
		} else {
			// process deleted carry overs
			List<CarryoverRecord> origCarryOvers = carryoverSrv.findAllByEmplId(empl.getEmplId());
			
			for(CarryoverRecord r : origCarryOvers) {
				// if carry over record is not in current list -> delete it from DB
				if (! carryoversWrap.contains(r)) {
					carryoverSrv.delete(r);
				}
			}
			
			// save carry overs
			for (CarryoverRecord c : carryoversWrap.getCarryovers()) {
				//genericService.save(c);
				carryoverSrv.save(c);
			}

			@SuppressWarnings("unchecked")
			List<CarryoverRecord> carryoversAfterSave = carryoverSrv.findAllByEmplId(empl.getEmplId());
			CarryoverRecordsWrapper carryoversAfterWrap = new CarryoverRecordsWrapper(carryoversAfterSave);
			theModel.addAttribute("carryoversWrap", carryoversAfterWrap);
			userMessage = new UserMessage("Successufly saved carry overs!");
			userMessage.setIsErrorMessage(false);
		}

		theModel.addAttribute("usermessage", userMessage);
		theModel.addAttribute("employee", empl);

		return "carryovers/employee-form-carryover";

	}

	/**
	 * find duplicate carryover years
	 * 
	 * @param List of CarryoverRecords
	 * @return
	 */
	private DuplicateYear foundDuplicatCarryoverYear(List<CarryoverRecord> carryovers) {

		DuplicateYear dupYear = new DuplicateYear(false, 0);

		Optional<CarryoverRecord> dupsFound;

		List<CarryoverRecord> tmpCarryover = new ArrayList<>();
		if (carryovers != null && !carryovers.isEmpty()) {
			for (CarryoverRecord v : carryovers) {
				dupsFound = tmpCarryover.stream().filter(c -> c.getVacationYear() == (v.getVacationYear())).findAny();
				if (dupsFound.isPresent()) {
					// found duplicate vacation object
					dupYear.setFound(true);
					dupYear.setDupYear(v.getVacationYear());
					return dupYear;
				} else {
					tmpCarryover.add(v);
				}
			}
		}

		return dupYear;
	}

}
