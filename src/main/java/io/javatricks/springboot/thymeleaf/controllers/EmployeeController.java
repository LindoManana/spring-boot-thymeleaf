package io.javatricks.springboot.thymeleaf.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import io.javatricks.springboot.thymeleaf.entities.Employee;
import io.javatricks.springboot.thymeleaf.exception.ResourceNotFoundException;
import io.javatricks.springboot.thymeleaf.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@ModelAttribute
	public void addDepartments(Model model) {
		List<String> departments = new ArrayList<String>();
		departments.add("IT");
		departments.add("HR");
		departments.add("Finance");
		departments.add("Marketing");
		model.addAttribute("departments", departments);
	}

	@GetMapping("/")
	public String viewAllEmployees(Model model) {
		List<Employee> employeesList = employeeService.getAllEmployees();

		model.addAttribute("employees", employeesList);
		return "list-employees";
	}

	@GetMapping("/employees/add")
	public String addEmployee(Employee employee) {
		return "add-employee";
	}

	@PostMapping("/employees/add")
	public String addStudent(@Valid Employee employee, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-employee";
		}

		employeeService.createOrUpdateEmployee(employee);

		return "redirect:/";
	}

	@GetMapping("/employees/edit/{id}")
	public String updateEmployee(@PathVariable("id") long employeeId, Model model) throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId);
		model.addAttribute("employee", employee);
		return "update-employee";
	}

	@PostMapping("/employees/edit")
	public String updateEmployee(@Valid Employee employee, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "update-employee";
		}

		employeeService.createOrUpdateEmployee(employee);

		return "redirect:/";
	}

	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable("id") long employeeId, Model model) throws Exception {
		employeeService.deleteEmployee(employeeId);

		return "redirect:/";
	}
}
