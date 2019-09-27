package com.luv2code.springboot.thymleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymleafdemo.entity.Employee;
import com.luv2code.springboot.thymleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	//here @Autowired is optional..since there is only one constructor
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService=theEmployeeService;
	}
	
	
	//add mapping for "/list"
	
	@GetMapping("/list")
	public String listEmployees(Model theModel){
	
		List<Employee> theEmployees=employeeService.findAllByOrderByLastNameAsc();
		
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);	
		
		return "employees/list-employees";	
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
	//create model attribute to bind form data
	Employee theEmployee=new Employee();
	
	theModel.addAttribute("employee", theEmployee);
	
	return "employees/employees-form";
		
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		
	System.out.println("------------The ID is: ------------"+theId);
	//get the employee From the Service
	Employee theEmployee= employeeService.findById(theId);
	
	//set employee as a model attribute to pre populate the form
	theModel.addAttribute("employee", theEmployee);
		
	//send over to our form
	return "employees/employees-form";
		
	}
	
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		//save the Employee
		employeeService.save(theEmployee);
		
		//use redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
	//delete the employee	
	employeeService.deleteById(theId);	
		
	//redirect to /employees/list	
	return "redirect:/employees/list";
	}
	
	
	
	
	
	
}
