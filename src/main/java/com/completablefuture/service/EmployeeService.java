package com.completablefuture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.completablefuture.entity.Employee;
import com.completablefuture.entity.EmployeeJson;
import com.completablefuture.repository.EmployeeJsonRepository;
import com.completablefuture.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repo;
	
	@Autowired
	EmployeeJsonRepository jsonRepo;
	
	public List<Employee> saveEmployees(List<Employee> empList) {
		List<Employee> savedEmployees= repo.saveAll(empList);
		return savedEmployees;
	}
	
	public List<EmployeeJson> saveJsonEmployees(List<EmployeeJson> empList){
	return jsonRepo.saveAll(empList);
	}
}
