package com.example.ems1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name="employees")
public class Employee {
	@Id
	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is mandatory")
	private String email;
	@NotBlank(message = "name should't be null, Empty and blank")
	private String name;
	@Positive(message = "salary should be more than Rs:0")
	private double salary;
	@NotBlank(message = "name should't be null, Empty and blank")
	private String dept;

}
