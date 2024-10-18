package com.completablefuture.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.completablefuture.entity.EmployeeJson;
import com.completablefuture.repository.EmployeeJsonRepository;

@Service
public class ComposeCombineCompletableFuture {

	@Autowired
	EmployeeJsonRepository jsonRepo;
	
	/*
	 * Get employee detail based on Id
	 */
	public CompletableFuture<EmployeeJson> getEmployeeById(String id) {
	 return CompletableFuture.supplyAsync(()->{
		 System.out.println("Thread for getAllEmployee "+Thread.currentThread().getName());
			return jsonRepo.findAll();
		}).thenApply((emp)->{
			 System.out.println("Thread for getEmployeeId "+Thread.currentThread().getName());
			return emp.stream().filter(x->id.equalsIgnoreCase(x.getEmployeeId())).findAny().orElse(null);
		});
	}
	
	/*
	 * get rating based on employee
	 */
	public CompletableFuture<Integer> getRating(EmployeeJson employee) {
		return CompletableFuture.supplyAsync(()->{
			 System.out.println("Thread for getRating "+Thread.currentThread().getName());
			return employee.getRating();
		});
	}
	
	/*
	 * get count based on gender
	 */
	public CompletableFuture<Map<String,Long>> getEmpCountBasedOnGender() throws InterruptedException, ExecutionException {
		return CompletableFuture.supplyAsync(()->{
			System.out.println("Thread for getAllEmployees "+Thread.currentThread().getName());
			 return jsonRepo.findAll();
		 }).thenApply((emp)->{
			 System.out.println("Thread for groupByGender "+Thread.currentThread().getName());
			 return emp.stream().collect(Collectors.groupingBy(x->x.getGender(),Collectors.counting()));
		 });
		 
	}
	
	/*
	 * get email for all employees
	 */
	public CompletableFuture<List<String>> getEmail() throws InterruptedException, ExecutionException {
		return CompletableFuture.supplyAsync(()->{
			 System.out.println("Thread for getEmail "+Thread.currentThread().getName());
			return jsonRepo.findAll().stream().map(x->x.getEmail()).collect(Collectors.toList());
		});
		
	}

}
