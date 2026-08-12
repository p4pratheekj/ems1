package com.example.ems1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems1.entity.Employee;
import com.example.ems1.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping
	public String createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee); 
	}
	
	@GetMapping("/{id}")
	public Employee fetchEmployeeById(@PathVariable String id) {
		return employeeService.fetchEmployeeById(id);
	}
	
	@GetMapping
	public List<Employee> fetchAllEmployees() {
		return employeeService.fetchAllEmployees();
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployeeById(@PathVariable String id) {
		return employeeService.deleteEmployeeById(id);
	}
	
	@PutMapping
	public String updateEmployeeById(@RequestBody Employee employee) {
		return employeeService.updateEmployeeById(employee,id);
	}
}
