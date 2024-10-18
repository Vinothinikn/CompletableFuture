package com.completablefuture.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee_Json")
public class EmployeeJson {


	@Id
	String employeeId;
	String firstName;
	String lastName;
	String email;
	String gender;
	String newJoiner;
	String learningPending;
	int salary;
	int rating;
	
	public EmployeeJson() {
		
	}
	public EmployeeJson(String employeeId, String firstName, String lastName, String email, String gender,
			String newJoiner, String learningPending, int salary, int rating) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.newJoiner = newJoiner;
		this.learningPending = learningPending;
		this.salary = salary;
		this.rating = rating;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNewJoiner() {
		return newJoiner;
	}
	public void setNewJoiner(String newJoiner) {
		this.newJoiner = newJoiner;
	}
	public String getLearningPending() {
		return learningPending;
	}
	public void setLearningPending(String learningPending) {
		this.learningPending = learningPending;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
