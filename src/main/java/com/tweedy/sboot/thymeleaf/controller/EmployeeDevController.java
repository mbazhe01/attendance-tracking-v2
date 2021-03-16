package com.tweedy.sboot.thymeleaf.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tweedy.sboot.thymeleaf.UserMessage;
import com.tweedy.sboot.thymeleaf.entity.Address;
import com.tweedy.sboot.thymeleaf.entity.AddressType;
import com.tweedy.sboot.thymeleaf.entity.Department;
import com.tweedy.sboot.thymeleaf.entity.EmployeeDev;
import com.tweedy.sboot.thymeleaf.entity.NoteDev;
import com.tweedy.sboot.thymeleaf.entity.Phone;
import com.tweedy.sboot.thymeleaf.entity.PhoneType;
import com.tweedy.sboot.thymeleaf.service.AddressService;
import com.tweedy.sboot.thymeleaf.service.DepartmentService;
import com.tweedy.sboot.thymeleaf.service.EmployeeService;
import com.tweedy.sboot.thymeleaf.service.PhoneService;

@Controller
@RequestMapping("/employeesdev")
public class EmployeeDevController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private PhoneService phoneService;

	@Autowired
	private AddressService addrService;

	@Value("${media.location}")
	private String mediaPath;

	@Value("${media.deafult.picture}")
	private String defaultPicture;

	@Value("${media.image.width}")
	private int imgWidth;
	
	@Value("${media.image.height}")
	private int imgHeight;
	
	// add mapping "/employeesdev"
	@GetMapping("/list")
	public String getEmployees(Model theModel) {

		List<EmployeeDev> theEmployees = employeeService.findAllEmployeeDev();

		theModel.addAttribute("employees", theEmployees);
		return "employeesdev/list-employees-dev";

	}

	// add employee
	@GetMapping("/add")
	public String showFormForAdd(Model theModel) {

		// for Departments drop down box
		List<Department> departments = departmentService.findAll();

		theModel.addAttribute("employee", new EmployeeDev());
		theModel.addAttribute("usermessage", new UserMessage(null));
		theModel.addAttribute("departments", departments);
		return "employeesdev/employee-form-add";

	}

	@PostMapping("/addNew")
	public String add(@Valid @ModelAttribute("employee") EmployeeDev employee, Errors errors,
			RedirectAttributes redirectAttributes, Model theModel) throws IOException {

		// for Departments drop down box
		// List<Department> departments = departmentService.findAll();

		if (null != errors && errors.getErrorCount() > 0) {
			List<ObjectError> errs = errors.getAllErrors();
			String errMsg = "";

			for (ObjectError e : errs)
				errMsg += e.getDefaultMessage() + " ";

			theModel.addAttribute("message", "Employee Edit failed. " + errMsg);
			theModel.addAttribute("alertClass", "alert-danger");
			return "employeesdev/employee-form-add";
		}

		// employee.activeStatusFromBoolToInt(); // from Thymeleaf to sql
		employee.syncFromTL();
		try {
			employeeService.save(employee);
		} catch (Exception ex) {
			theModel.addAttribute("message", "Add employee failed." + ex.getCause().getCause().getLocalizedMessage());
			theModel.addAttribute("alertClass", "alert-danger");

			return "employeesdev/employee-form-add";
		}

		redirectAttributes.addFlashAttribute("message",
				"Employee " + employee.toString() + " added. You can add another employee here...");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		return "redirect:/employeesdev/add";
	}// eof

	// update employee
	@GetMapping("/edit/{id}")
	public String saveEdit(@PathVariable int id, Model theModel) {

		EmployeeDev theEmployee = employeeService.findEmployeeById(id);
		theEmployee.activeStatusFromIntToBool();
		theEmployee.assignFakeIds();
		// for Departments drop down box
		List<Department> departments = departmentService.findAll();
		// for Phone Label drop box
		List<PhoneType> phoneTypes = phoneService.getPhoneTypes();
		// for address label drop box
		List<AddressType> addrTypes = addrService.getAddressTypes();

		String scrollToSection = "Top";
		theModel.addAttribute("scrollto", scrollToSection);
		theModel.addAttribute("employee", theEmployee);
		theModel.addAttribute("departments", departments);
		theModel.addAttribute("phonetypes", phoneTypes);
		theModel.addAttribute("addrtypes", addrTypes);
		theModel.addAttribute("usermessage", new UserMessage(null));

		return "employeesdev/employee-form-edit";

	}// eof

	// update employee
	@Transactional
	@PostMapping("/edit")
	public String showFormForUpdate(@Valid @ModelAttribute("employee") EmployeeDev employee,
			BindingResult bindingResult, Errors errors, RedirectAttributes redirectAttributes, MultipartFile file,
			Model theModel, @RequestParam(required = false, value = "newnote") String newNote,
			@RequestParam(required = false, value = "newaddress") String newAddress,
			@RequestParam(required = false, value = "newphone") String newPhone) throws IOException {

		EmployeeDev currentEmployee = employeeService.findEmployeeById(employee.getEmplId());
		String scrollToSection = "Top";
		// for Phone Label drop box
		List<PhoneType> phoneTypes = phoneService.getPhoneTypes();
		// for address label drop box
		List<AddressType> addrTypes = addrService.getAddressTypes();

		if (null != errors && errors.getErrorCount() > 0) {

			
			byte[] tmpBytes = file.getBytes();
			String tmpFileName = "tmp" + file.getOriginalFilename();
									
			Path tmpPath = Paths.get(mediaPath + tmpFileName);
			
			if (!file.isEmpty()) {
				if (tmpFileName.endsWith("jpg") || tmpFileName.endsWith("png")) 
					Files.write(tmpPath, tmpBytes);
			}
						
			List<ObjectError> errs = errors.getAllErrors();
			String errMsg = "";

			for (ObjectError e : errs)
				errMsg += e.getDefaultMessage();

			theModel.addAttribute("selectedFile", tmpPath);
			theModel.addAttribute("message", "Employee Edit failed. " + errMsg);
			theModel.addAttribute("alertClass", "alert-danger");
			return "employeesdev/employee-form-edit";
		}

		else if (newPhone != null) {
			// add new phone line
			employee.purgeEmptyPhones();
			employee.addPhone();
			employee.assignFakeIds();

			scrollToSection = "PhoneSection";
			theModel.addAttribute("scrollto", scrollToSection);
			theModel.addAttribute("phonetypes", phoneTypes);
			theModel.addAttribute("addrtypes", addrTypes);
			return "employeesdev/employee-form-edit";

		}

		else if (newAddress != null) {
			// add new address line

			scrollToSection = "AddressSection";
			theModel.addAttribute("scrollto", scrollToSection);
			theModel.addAttribute("phonetypes", phoneTypes);
			theModel.addAttribute("addrtypes", addrTypes);
			employee.purgeEmptyAddresses();
			employee.addAddress();
			employee.assignFakeIds();

			return "employeesdev/employee-form-edit";

		} else if (newNote != null) {
			// add new note line
			scrollToSection = "NotesSection";
			theModel.addAttribute("scrollto", scrollToSection);
			theModel.addAttribute("phonetypes", phoneTypes);
			theModel.addAttribute("addrtypes", addrTypes);
			employee.addNote();
			return "employeesdev/employee-form-edit";

		}

		else {

			boolean fileOK = false;
			byte[] bytes = file.getBytes();
			String fileName = file.getOriginalFilename();
									
			Path path = Paths.get(mediaPath + fileName);
			
			if (!file.isEmpty()) {
				if (fileName.endsWith("jpg") || fileName.endsWith("png")) {
					fileOK = true;
				}

			} else {
				fileOK = true;
			}

			if (!fileOK) {
				theModel.addAttribute("message", "Image file has to be jpg or png.");
				theModel.addAttribute("alertClass", "alert-danger");
				return "employeesdev/employee-form-edit";
			}

			// employee.activeStatusFromBoolToInt(); // from Thymeleaf to sql
			employee.syncFromTL();

			try {
				employee.purgeEmptyPhones();
				employee.purgeEmptyAddresses();
				employee.purgeEmptyNotes();

				List<Phone> curPhones = employee.getPhones();
				List<Phone> origPhones = employeeService.getPhonesByEmployee(employee);
				List<Phone> phonesToDelete = employeeService.notInListPhones(origPhones, curPhones);

				List<Address> curAddrs = employee.getAddresses();
				List<Address> origAddrs = employeeService.getAddressesByEmployee(employee);
				List<Address> addrsToDelete = employeeService.notInListAddresses(origAddrs, curAddrs);

				List<NoteDev> curNotes = employee.getNotes();
				List<NoteDev> origNotes = employeeService.getNotesByEmployee(employee);
				List<NoteDev> notesToDelete = employeeService.notInListNotes(origNotes, curNotes);

				employeeService.deleteAddresses(addrsToDelete);
				employeeService.deletePhones(phonesToDelete);
				employeeService.deleteNotes(notesToDelete);

				if (!file.isEmpty()) {

					if (currentEmployee.getImage()!=null && !currentEmployee.getImage().equals(defaultPicture)) {
						Path path2 = Paths.get(mediaPath + currentEmployee.getImage());
						Files.deleteIfExists(path2);
					}

					employee.setImage(fileName); // new image file
					
					Files.write(path, bytes);
					
					//ResizeImage resizeImage = new ResizeImage(imgWidth, imgHeight, path, resizedPath);
					//resizeImage.resizeAndWrite();
					

				} else {
					employee.setImage(employee.getImage());
				}
				if(employee.getImage()==null)
					employee.setImage(currentEmployee.getImage());
				employeeService.save(employee);

			} catch (Exception ex) {
				String err = null;
				if (ex.getCause() != null)
					err = ex.getCause().getCause().getLocalizedMessage();
				theModel.addAttribute("message", "Employee edit failed." + (err == null ? ex.getMessage() : err));
				theModel.addAttribute("alertClass", "alert-danger");

				return "employeesdev/employee-form-edit";
			}

			redirectAttributes.addFlashAttribute("message", "Employee " + employee.toString() + " edited.");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");

			return "redirect:/employeesdev/edit/" + employee.getEmplId();
		}
	}// eof

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes redirectAttributes, Model theModel)
			throws IOException {

		EmployeeDev employee = employeeService.findEmployeeById(id);

		try {
			employeeService.deleteEmployee(employee);

		} catch (HibernateException eh) {
			List<EmployeeDev> theEmployees = employeeService.findAllEmployeeDev();

			theModel.addAttribute("message", "Deleted employee failed." + eh.getLocalizedMessage());
			theModel.addAttribute("alertClass", "alert-danger");
			theModel.addAttribute("employees", theEmployees);

			return "employeesdev/list-employees-dev";

		}

		catch (Exception ex) {

			List<EmployeeDev> theEmployees = employeeService.findAllEmployeeDev();

			theModel.addAttribute("message",
					"Deleted employee failed." + ex.getCause().getCause().getLocalizedMessage());
			theModel.addAttribute("alertClass", "alert-danger");
			theModel.addAttribute("employees", theEmployees);

			return "employeesdev/list-employees-dev";

		}

		redirectAttributes.addFlashAttribute("message", "Deleted employee " + employee.toString() + ".");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		return "redirect:/employeesdev/list";
	}

}
