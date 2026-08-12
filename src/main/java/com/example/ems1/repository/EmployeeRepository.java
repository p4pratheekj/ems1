package com.example.ems1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ems1.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
