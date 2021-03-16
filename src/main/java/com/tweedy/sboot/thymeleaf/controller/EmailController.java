package com.tweedy.sboot.thymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tweedy.sboot.thymeleaf.UserMessage;
import com.tweedy.sboot.thymeleaf.entity.Email;
import com.tweedy.sboot.thymeleaf.entity.EmailsWrapper;
import com.tweedy.sboot.thymeleaf.entity.Employee;
import com.tweedy.sboot.thymeleaf.entity.EmployeeDev;
import com.tweedy.sboot.thymeleaf.entity.EmployeeTL;
import com.tweedy.sboot.thymeleaf.service.EmployeeService;
import com.tweedy.sboot.thymeleaf.service.GenericService;


@Controller
@RequestMapping("/emails")
public class EmailController {
	private GenericService genericService;
	private EmployeeService employeeService;
	
	@Autowired
	public EmailController(@Qualifier("employeeServiceImpl")  EmployeeService theEmployeeService,
			@Qualifier("genericServiceImpl")  GenericService theGenericService
			) {
		employeeService = theEmployeeService;
		genericService = theGenericService;
	}
	
	// update employee notes
	@GetMapping("/showFormForEmailUpdate")
	public String showFormForNoteUpdate(@RequestParam("employeeId") String theId,
					Model theModel) throws NumberFormatException, Exception {
						
		Employee theEmployee = employeeService.findById(Integer.parseInt(theId));
					
		@SuppressWarnings("unchecked")
		List<Email> emails = (List<Email>) genericService.findAll(Integer.parseInt(theId),
															"Email");
				
		EmailsWrapper emailsWrap = new EmailsWrapper(emails);
					
		theModel.addAttribute("employee", theEmployee);
		theModel.addAttribute("emailwrap", emailsWrap);
		theModel.addAttribute("usermessage", new UserMessage(null));
						
		return "emails/employee-form-email";
						
	}// end of showFormForEmailUpdate
	
	// add employee email
	@GetMapping("/addEmail")
	public String addEmail(
		@RequestParam("employeeId") String theId,
		Model theModel) throws Exception {
					
		EmployeeDev empl = employeeService.findEmployeeById(Integer.parseInt(theId));
		//EmployeeTL employee = empl.convertToEmployeeTL();			
		// get employee emails
		@SuppressWarnings("unchecked")
		List<Email> emails = (List<Email>) genericService.findAll(Integer.parseInt(theId),
														"Email");
			
		// add one empty email
		Email email = new Email();
		email.setEmployee(empl);
		emails.add(email);
					
		// return to email	
		EmailsWrapper emailWrap = new EmailsWrapper(emails);
		theModel.addAttribute("employee", empl);
		theModel.addAttribute("emailwrap", emailWrap);
		theModel.addAttribute("usermessage", new UserMessage(null));
		return "emails/employee-form-email";
						
	}// eof addEmail
	
	// save employee email
	@PostMapping("/saveEmail")
	public String saveEmail(
				@ModelAttribute EmailsWrapper emailsWrap,
				Model theModel) throws Exception {
					
			// save emails
			for(Email e: emailsWrap.getEmails()) 
				genericService.save(e);
					
			Employee empl = employeeService.findById(emailsWrap.getEmployeeId());
			EmployeeTL employee = empl.convertToEmployeeTL();
			
			@SuppressWarnings("unchecked")
			List<Email> emailsAfterSave = (List<Email>) genericService.findAll(empl.getEmplId(),
														"Email");
			EmailsWrapper emailWrap = new EmailsWrapper(emailsAfterSave);
					
			theModel.addAttribute("employee", employee);
			theModel.addAttribute("usermessage", new UserMessage("Sucessufly saved emails!"));
			theModel.addAttribute("emailwrap", emailWrap);
					
			// show employee emails form with save result message on the page
			return "emails/employee-form-email";
	}// eof saveEmail
	
	// delete email
	@GetMapping("/deleteEmail")
	public String deleteEmail(@RequestParam("emailId") int theId,
							Model theModel) throws Exception {
						
		int employeeId = genericService.getEmployeeByEntityId(theId, "Email", "emailId");
		genericService.delete(theId, "Email", "emailId");
					
		// generate save result message
		UserMessage userMessage = new UserMessage("Successufly deleted email!");
						
		EmailsWrapper emailWrap = new EmailsWrapper((List<Email>) genericService.findAll(employeeId, "Email"));
		Employee empl = employeeService.findById(employeeId);
							
		theModel.addAttribute("employee", empl);
		theModel.addAttribute("usermessage", userMessage);
		theModel.addAttribute("emailwrap", emailWrap);
		
		return "/emails/employee-form-email";
						
	}// eof deleteEmail()
	
}//eoc
