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

import com.tweedy.sboot.thymeleaf.entity.AttendanceCode;
import com.tweedy.sboot.thymeleaf.entity.Department;
import com.tweedy.sboot.thymeleaf.service.AttendanceCodeService;
import com.tweedy.sboot.thymeleaf.service.DepartmentService;

@Controller
@RequestMapping("/attendancecodes")
public class AttendanceCodeController {
	private AttendanceCodeService attendanceCodeService;
	
	@Autowired
	public AttendanceCodeController(@Qualifier("attendanceCodeServiceImpl") AttendanceCodeService theService) {
		attendanceCodeService = theService;
	}
	
	// add mapping 
	@GetMapping("/list")
	public String getAttendanceCodes(Model theModel) {
				
		List<AttendanceCode> theAttendanceCodes = attendanceCodeService.findAll();
				
		theModel.addAttribute("attendancecodes", theAttendanceCodes);
		return "attendancecodes/list-attendance-codes";
				
	}
		
	// add attendance code
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
				
		AttendanceCode theAttendanceCode = new AttendanceCode();
				
		theModel.addAttribute("attendancecode", theAttendanceCode);
			return "attendancecodes/attendance-code-form";
				
	}
		
	// save department
	@PostMapping("/save")
	public String saveAttendanceCode( 
			//@ModelAttribute("attendancecode") AttendanceCode theAttendanceCode
			@ModelAttribute("attendancecode") AttendanceCode theAttendanceCode
			
			) {
					
			attendanceCodeService.save(theAttendanceCode);
				
			return "redirect:/attendancecodes/list";
					
		}
		
	// update department
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("attendanceCodeId") int theId,
		Model theModel) {
				
		AttendanceCode theAttendanceCode = attendanceCodeService.findById(theId);
					
		theModel.addAttribute("attendancecode", theAttendanceCode);
		return "attendancecodes/attendance-code-form";
					
	}
		
		// delete department
		@GetMapping("/delete")
		public String delete(@RequestParam("attendanceCodeId") int theId) {
					
			attendanceCodeService.deleteById(theId);
				
			return "redirect:/attendancecodes/list";
					
		}
	
}
