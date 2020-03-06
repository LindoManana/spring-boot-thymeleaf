package io.javatricks.springboot.thymeleaf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javatricks.springboot.thymeleaf.entities.Employee;
import io.javatricks.springboot.thymeleaf.exception.ResourceNotFoundException;
import io.javatricks.springboot.thymeleaf.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id :: " + employeeId));
		return employee;
	}

	public Employee createOrUpdateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Map<String, Boolean> deleteEmployee(Long employeeId) throws Exception {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id :: " + employeeId));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
