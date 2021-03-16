package com.tweedy.sboot.thymeleaf.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tweedy.sboot.thymeleaf.UserMessage;
import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.AddressLabel;
import com.tweedy.sboot.thymeleaf.entity.AddressesWrapper;
import com.tweedy.sboot.thymeleaf.entity.Department;
import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.EmployeeDev;
import com.tweedy.sboot.thymeleaf.entity.EmployeeTL;
import com.tweedy.sboot.thymeleaf.entity.Note;
import com.tweedy.sboot.thymeleaf.entity.NoteTL;
import com.tweedy.sboot.thymeleaf.entity.NotesWrapper;
import com.tweedy.sboot.thymeleaf.entity.NotesWrapperTL;
import com.tweedy.sboot.thymeleaf.entity.Phone;
import com.tweedy.sboot.thymeleaf.entity.PhonesWrapper;
import com.tweedy.sboot.thymeleaf.service.AddressLabelService;
import com.tweedy.sboot.thymeleaf.service.AddressService;
import com.tweedy.sboot.thymeleaf.service.DepartmentService;
import com.tweedy.sboot.thymeleaf.service.EmployeeService;
import com.tweedy.sboot.thymeleaf.service.GenericService;
import com.tweedy.sboot.thymeleaf.service.PhoneService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	private AddressService addressService;
	private PhoneService phoneService;
	private AddressLabelService addressLabelService;
	private DepartmentService departmentService;
	private GenericService genericService;
	// @Qualifier("employeeService")

	@Autowired
	public EmployeeController(@Qualifier("employeeServiceImpl") EmployeeService theEmployeeService,
			@Qualifier("addressServiceImpl") AddressService theAddressService,
			@Qualifier("addressLabelServiceImpl") AddressLabelService theAddressLabelService,
			@Qualifier("phoneServiceImpl") PhoneService thePhoneService,
			@Qualifier("genericServiceImpl") GenericService theGenericService,
			@Qualifier("departmentServiceImpl") DepartmentService theDepartmentService) {
		employeeService = theEmployeeService;
		addressService = theAddressService;
		addressLabelService = theAddressLabelService;
		phoneService = thePhoneService;
		departmentService = theDepartmentService;
		genericService = theGenericService;
	}

	// add mapping "/employee"
	@GetMapping("/list")
	public String getEmployees(Model theModel) {

		List<Employee> theEmployees = employeeService.findAll();

		theModel.addAttribute("employees", theEmployees);
		return "employees/list-employees";

	}

	// add employee
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Employee theEmployee = new Employee();

		EmployeeTL employeeTL = theEmployee.convertToEmployeeTL();

		// for Departments drop down box
		List<Department> departments = departmentService.findAll();

		theModel.addAttribute("employee", employeeTL);
		theModel.addAttribute("usermessage", new UserMessage(null));
		theModel.addAttribute("departments", departments);
		return "employees/employee-form";

	}

	// update employee
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") String theId, Model theModel) {

		Employee theEmployee = employeeService.findById(Integer.parseInt(theId));
		EmployeeTL employeeTL = theEmployee.convertToEmployeeTL();

		// for Departments drop down box
		List<Department> departments = departmentService.findAll();

		theModel.addAttribute("employee", employeeTL);
		theModel.addAttribute("departments", departments);
		theModel.addAttribute("usermessage", new UserMessage(null));

		return "employees/employee-form";

	}

	// add employee address
	@GetMapping("/addAddress")
	public String addAddress(@RequestParam("employeeId") String theId, Model theModel) {

		EmployeeDev empl = employeeService.findEmployeeById(Integer.parseInt(theId));
		//EmployeeTL employee = empl.convertToEmployeeTL();

		// get employee addresses
		List<Address> addresses = addressService.findAll(empl.getEmplId());

		// add one empty address
		Address address = new Address();
		address.setEmployee(empl);
		addresses.add(address);

		// values for address label drop down
		List<AddressLabel> addrLabels = addressLabelService.findAll();

		// return to Addresses page
		AddressesWrapper addrWrap = new AddressesWrapper(addresses);
		theModel.addAttribute("employee", empl);
		theModel.addAttribute("addrwrap", addrWrap);
		theModel.addAttribute("addrlabels", addrLabels);
		theModel.addAttribute("usermessage", new UserMessage(null));
		return "employees/employee-form-address";

	}

	// add employee phone
	@GetMapping("/addPhone")
	public String addPhone(@RequestParam("employeeId") String theId, Model theModel) {

		EmployeeDev employee = employeeService.findEmployeeById(Integer.parseInt(theId));
		//EmployeeTL employee = empl.convertToEmployeeTL();

		// get employee phones
		List<Phone> phones = phoneService.findAll(employee.getEmplId());

		// add one empty phone
		Phone phone = new Phone();
		phone.setEmployee(employee);
		phones.add(phone);

		// values for address label drop down
		// List<AddressLabel> addrLabels = addressLabelService.findAll();

		// return to phone page
		PhonesWrapper phoneWrap = new PhonesWrapper(phones);
		theModel.addAttribute("employee", employee);
		theModel.addAttribute("phonewrap", phoneWrap);
		// theModel.addAttribute("addrlabels", addrLabels);
		theModel.addAttribute("usermessage", new UserMessage(null));
		return "employees/employee-form-phone";

	}

	// add employee note
	@GetMapping("/addNote")
	public String addNote(@RequestParam("employeeId") String theId, Model theModel) throws Exception {

		Employee empl = employeeService.findById(Integer.parseInt(theId));
		EmployeeTL employee = empl.convertToEmployeeTL();

		// get employee notes
		@SuppressWarnings("unchecked")
		List<Note> notes = (List<Note>) genericService.findAll(Integer.parseInt(theId), "Note");
		NotesWrapper notesWrap = new NotesWrapper(notes);

		// add one empty note
		Note note = new Note();
		note.setEmplId(employee.getEmplId());
		LocalDateTime now = LocalDateTime.now();
		note.setNoteDate(Timestamp.valueOf(now));
		notes.add(note);

		// return to phone page
		NotesWrapper noteWrap = new NotesWrapper(notes);
		theModel.addAttribute("employee", employee);
		theModel.addAttribute("notewrap", noteWrap);
		theModel.addAttribute("usermessage", new UserMessage(null));
		return "employees/employee-form-note";

	}

	// update employee address
	@GetMapping("/showFormForAddressUpdate")
	public String showFormForAddressUpdate(@RequestParam("employeeId") String theId, Model theModel) {

		Employee theEmployee = employeeService.findById(Integer.parseInt(theId));
		EmployeeTL employeeTL = theEmployee.convertToEmployeeTL();

		List<Address> addresses = addressService.findAll(Integer.parseInt(theId));
		AddressesWrapper addrWrap = new AddressesWrapper(addresses);

		// values for address label drop down
		List<AddressLabel> addrLabels = addressLabelService.findAll();
		// AddressLabelsWrapper addrLabelWrap = new AddressLabelsWrapper(addrLabels);

		theModel.addAttribute("employee", employeeTL);
		theModel.addAttribute("addrwrap", addrWrap);
		theModel.addAttribute("addrlabels", addrLabels);
		theModel.addAttribute("usermessage", new UserMessage(null));

		return "employees/employee-form-address";

	}

	// update employee phones
	@GetMapping("/showFormForPhoneUpdate")
	public String showFormForPhoneUpdate(@RequestParam("employeeId") String theId, Model theModel) {

		Employee theEmployee = employeeService.findById(Integer.parseInt(theId));
		EmployeeTL employeeTL = theEmployee.convertToEmployeeTL();

		List<Phone> phones = phoneService.findAll(Integer.parseInt(theId));
		PhonesWrapper phoneWrap = new PhonesWrapper(phones);

		// values for address label drop down
		// List<AddressLabel> addrLabels = addressLabelService.findAll();

		theModel.addAttribute("employee", employeeTL);
		theModel.addAttribute("phonewrap", phoneWrap);
		// theModel.addAttribute("addrlabels", addrLabels);
		theModel.addAttribute("usermessage", new UserMessage(null));

		return "employees/employee-form-phone";

	}

	// update employee notes
	@GetMapping("/showFormForNoteUpdate")
	public String showFormForNoteUpdate(@RequestParam("employeeId") String theId, Model theModel)
			throws NumberFormatException, Exception {

		Employee theEmployee = employeeService.findById(Integer.parseInt(theId));
		EmployeeTL employeeTL = theEmployee.convertToEmployeeTL();

		@SuppressWarnings("unchecked")
		List<Note> notes = (List<Note>) genericService.findAll(Integer.parseInt(theId), "Note");
		// format date of the note
		// List<NoteTL> notesTL = new ArrayList<>();
		// notes.forEach(note-> notesTL.add( note.convertToNoteTL()));

		NotesWrapper notesWrap = new NotesWrapper(notes);

		// values for address label drop down
		// List<AddressLabel> addrLabels = addressLabelService.findAll();

		theModel.addAttribute("employee", employeeTL);
		theModel.addAttribute("notewrap", notesWrap);
		theModel.addAttribute("usermessage", new UserMessage(null));

		return "employees/employee-form-note";

	}

	// save employee
	@PostMapping("/save")
	public String saveEmployee(
			// @ModelAttribute("employeeTL") EmployeeTL theEmployee,
			@Valid EmployeeTL theEmployee, BindingResult bindingResult,
			Errors errors,
			RedirectAttributes redirectAttributes,
			Model theModel) {
			UserMessage userMassage = new UserMessage("");
		if (bindingResult.hasErrors()) {
			theModel.addAttribute("employee", theEmployee);
			return "employees/employee-form";
		}

		Employee employee = new Employee();

		employee.setEmplId(theEmployee.getEmplId());
		employee.setDepartmentId(theEmployee.getDepartmentId());
		employee.setLastName(theEmployee.getLastName());
		employee.setFirstName(theEmployee.getFirstName());
		employee.setMiddleName(theEmployee.getMiddleName());
		employee.setPhoneIntercomeExt(theEmployee.getPhoneIntercomeExt());
		employee.setDescription(theEmployee.getDescription());

		if (theEmployee.getActiveStatus() == true)
			employee.setActiveStatus(1);
		else
			employee.setActiveStatus(0);
		employee.setDefaultVacationDays(theEmployee.getDefaultVacationDays());
		employee.setStartDate(theEmployee.getStartDate());
		employee.setResignDate(theEmployee.getResignDate());

		employeeService.save(employee);

		// generate save result message
		UserMessage userMessage = new UserMessage("Sucessufly saved!");
		theModel.addAttribute("employee", employee.convertToEmployeeTL());
		theModel.addAttribute("usermessage", userMessage);

		// for Departments drop down box
		List<Department> departments = departmentService.findAll();
		theModel.addAttribute("departments", departments);

		// show employee update form with save result message on the page
		return "redirect:/employees/employee-form";

	}

	// save employee address
	@PostMapping("/saveAddress")
	public String saveEmployeeAddress(@ModelAttribute AddressesWrapper addrWrap, Model theModel) {

		// employeeService.save(employee);
		List<Address> addresses = addrWrap.getAddresses();

		// save addresses
		for (Address a : addresses)
			addressService.save(a);

		// generate save result message
		UserMessage userMessage = new UserMessage("Sucessufly saved!");

		Employee empl = employeeService.findById(addrWrap.getEmployeeId());
		EmployeeTL employee = empl.convertToEmployeeTL();

		// values for address label drop down
		List<AddressLabel> addrLabels = addressLabelService.findAll();

		theModel.addAttribute("employee", employee);
		theModel.addAttribute("usermessage", userMessage);
		theModel.addAttribute("addrlabels", addrLabels);
		theModel.addAttribute("addrwrap", addrWrap);

		// show employee update form with save result message on the page
		return "/employees/employee-form-address";

	}

	// save employee phone
	@PostMapping("/savePhone")
	public String saveEmployeePhone(@ModelAttribute PhonesWrapper phoneWrap, Model theModel) {

		List<Phone> phones = phoneWrap.getPhones();

		// save phones
		for (Phone p : phones)
			phoneService.save(p);

		// generate save result message
		UserMessage userMessage = new UserMessage("Sucessufly saved phones!");

		Employee empl = employeeService.findById(phoneWrap.getEmployeeId());
		EmployeeTL employee = empl.convertToEmployeeTL();

		// values for address label drop down
		// List<AddressLabel> addrLabels = addressLabelService.findAll();

		theModel.addAttribute("employee", employee);
		theModel.addAttribute("usermessage", userMessage);
		// theModel.addAttribute("addrlabels", addrLabels);
		theModel.addAttribute("phonewrap", phoneWrap);

		// show employee phones form with save result message on the page
		return "/employees/employee-form-phone";

	}

	// save employee note
	@PostMapping("/saveNote")
	public String saveEmployeeNote(@ModelAttribute NotesWrapperTL noteWrap, Model theModel) throws Exception {

		List<NoteTL> notes = noteWrap.getNotes();
		List<Note> notesToShow = new ArrayList<>();
		// save phones
		for (NoteTL n : notes) {

			genericService.save(n);

		}

		// generate save result message
		UserMessage userMessage = new UserMessage("Sucessufly saved notes!");

		Employee empl = employeeService.findById(noteWrap.getEmployeeId());
		EmployeeTL employee = empl.convertToEmployeeTL();

		@SuppressWarnings("unchecked")
		List<Note> notesAfterSave = (List<Note>) genericService.findAll(empl.getEmplId(), "Note");
		NotesWrapper notesWrap = new NotesWrapper(notesAfterSave);

		theModel.addAttribute("employee", employee);
		theModel.addAttribute("usermessage", userMessage);
		theModel.addAttribute("notewrap", noteWrap);

		// show employee phones form with save result message on the page
		return "/employees/employee-form-note";
	}

	// delete employee
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {

		employeeService.deleteById(theId);

		return "redirect:/employees/list";

	}

	// delete address
	@GetMapping("/deleteAddress")
	public String deleteAddress(@RequestParam("addrId") int theId, Model theModel) {

		int employeeId = addressService.getEmployeeByAddrId(theId);
		addressService.deleteAddress(theId);

		List<Address> addresses = addressService.findAll(employeeId);

		// generate save result message
		UserMessage userMessage = new UserMessage("Sucessufly deleted the address!");

		AddressesWrapper addrWrap = new AddressesWrapper(addressService.findAll(employeeId));
		Employee empl = employeeService.findById(employeeId);
		EmployeeTL employee = empl.convertToEmployeeTL();

		// values for address label drop down
		List<AddressLabel> addrLabels = addressLabelService.findAll();

		theModel.addAttribute("employee", employee);
		theModel.addAttribute("usermessage", userMessage);
		theModel.addAttribute("addrwrap", addrWrap);
		theModel.addAttribute("addrlabels", addrLabels);

		return "/employees/employee-form-address";

	}

	// delete phone
	@GetMapping("/deletePhone")
	public String deletePhone(@RequestParam("phoneId") int theId, Model theModel) {

		int employeeId = phoneService.getEmployeeByPhoneId(theId);
		phoneService.deletePhone(theId);

		// generate save result message
		UserMessage userMessage = new UserMessage("Sucessufly deleted the phone!");

		PhonesWrapper phoneWrap = new PhonesWrapper(phoneService.findAll(employeeId));
		Employee empl = employeeService.findById(employeeId);
		EmployeeTL employee = empl.convertToEmployeeTL();

		theModel.addAttribute("employee", employee);
		theModel.addAttribute("usermessage", userMessage);
		theModel.addAttribute("phonewrap", phoneWrap);

		return "/employees/employee-form-phone";

	}// eof deletePhone()

	// delete note
	@GetMapping("/deleteNote")
	public String deleteNote(@RequestParam("noteId") int theId, Model theModel) throws Exception {

		int employeeId = genericService.getEmployeeByEntityId(theId, "Note", "noteId");
		genericService.delete(theId, "Note", "noteId");

		// generate save result message
		UserMessage userMessage = new UserMessage("Sucessufly deleted the phone!");

		NotesWrapper noteWrap = new NotesWrapper((List<Note>) genericService.findAll(employeeId, "Note"));
		Employee empl = employeeService.findById(employeeId);
		EmployeeTL employee = empl.convertToEmployeeTL();

		// values for address label drop down
		// List<AddressLabel> addrLabels = addressLabelService.findAll();

		theModel.addAttribute("employee", employee);
		theModel.addAttribute("usermessage", userMessage);
		theModel.addAttribute("notewrap", noteWrap);
		// theModel.addAttribute("addrlabels", addrLabels);

		return "/employees/employee-form-note";

	}// eof deleteNote()

}// eoc
