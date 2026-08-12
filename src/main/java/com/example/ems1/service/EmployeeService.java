package com.example.ems1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.example.ems1.entity.Employee;
import com.example.ems1.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public String createEmployee(Employee employee) {
//		if(employeeRepository.existsById(employee.getId())) {
//			return "Employee data already exists";
//		}else {
			employeeRepository.save(employee);
			return "employee data inserted successfully";
		}
	

	public Employee fetchEmployeeById(String id) {
		Optional<Employee> oe = employeeRepository.findById(id);
		Employee employee = oe.get();
		return employee;
	}
	
	
	public List<Employee> fetchAllEmployees() {
		return employeeRepository.findAll();
	}
	

	public String deleteEmployeeById(String id) {
		if(!employeeRepository.existsById(id)) {
			return "Employee data not found";
		}else {
		 employeeRepository.deleteById(id);
		 return "Employee data deleted";
		}
	}
	
	
	public String updateEmployeeById(String id, Employee employee) {
	    if (!employeeRepository.existsById(id)) {
	        return("Employee not found with id: " + id);
	    }
	    employeeRepository.save(employee);
	    return "Employee data updated successfully";
	}
	

}
