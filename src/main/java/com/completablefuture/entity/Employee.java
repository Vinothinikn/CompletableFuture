package com.completablefuture.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee_Info_2")
public class Employee {
	@Id
	@Column(name = "eeId")
	private String id;
	
	@Column(name = "FullName")
	private String fullName;
	
	@Column(name = "JobTitle")
	private String jobTitle;
	
	@Column(name = "Department")
	private String department;
	
	@Column(name = "BusinessUnit")
	private String businessUnit;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Ethinicity")
	private String ethinicity;
	
	@Column(name = "Age")
	private String age;
	
	@Column(name = "HireDate")
	private String hireDate;
	
	@Column(name = "AnnualSalary")
	private String annualSalary;
	
	@Column(name = "Bonus")
	private String bonus;
	
	@Column(name = "Country")
	private String country;
	
	@Column(name = "City")
	private String city;
	
	@Column(name = "ExitDate")
	private String exitDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEthinicity() {
		return ethinicity;
	}

	public void setEthinicity(String ethinicity) {
		this.ethinicity = ethinicity;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(String annualSalary) {
		this.annualSalary = annualSalary;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getExitDate() {
		return exitDate;
	}

	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}
	
}
