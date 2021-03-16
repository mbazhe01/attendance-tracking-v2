package com.tweedy.sboot.thymeleaf.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tweedy.sboot.thymeleaf.entity.Department;
import com.tweedy.sboot.thymeleaf.service.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

	private DepartmentService departmentService;
	
	@Autowired
	public DepartmentController(@Qualifier("departmentServiceImpl") DepartmentService theService) {
		departmentService = theService;
	}
	
	// add mapping 
	@GetMapping("/list")
	public String getDepartments(Model theModel) {
			
		List<Department> theDepartments = departmentService.findAll();
			
		theModel.addAttribute("departments", theDepartments);
			return "departments/list-departments";
			
	}
	
	// add department
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
			
		Department theDepartment = new Department();
			
		theModel.addAttribute("department", theDepartment);
		return "departments/department-form";
			
	}
	
	// save department
	@PostMapping("/save")
	public String saveEmployee(@Valid @ModelAttribute("department") Department theDepartment,
			Errors errors) {
				
		if(errors.hasErrors())
			return "departments/department-form";
		
		departmentService.save(theDepartment);
			
		return "redirect:/departments/showFormForAdd";
				
	}
	
	// update department
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("departmentId") int theId,
			Model theModel) {
			
			Department theDepartment = departmentService.findById(theId);
				
				theModel.addAttribute("department", theDepartment);
				return "departments/department-form";
				
	}
	
	// delete department
	@GetMapping("/delete")
	public String delete(@RequestParam("departmentId") int theId) {
				
		departmentService.deleteById(theId);
			
		return "redirect:/departments/list";
				
	}
	
}

