package com.completablefuture.service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.completablefuture.entity.Employee;
import com.completablefuture.parserservice.ParserService;

@Service
public class BeforeJava8Example {
	
	private List<Employee> parsedList;
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(BeforeJava8Example.class);
	
	@Autowired
	ParserService csvParser;

	@Autowired
	EmployeeService empService;
	
	
	/**
	 * Using Future, we cannot combine the result or join the future 
	 * Also handling exception is also not possible
	 */
	public List<Employee> onlyFuture(MultipartFile file) throws InterruptedException, ExecutionException {
		
		ExecutorService service = Executors.newFixedThreadPool(10);
		Future<List<Employee>> future = service.submit(()->{
			return csvParser.csvParser(file);
		});
		
		List<Employee> empList = future.get();
		Future<List<Employee>> savedFuture = service.submit(()->{
			return empService.saveEmployees(empList);
		});
		
		List<Employee> savedEmployees = savedFuture.get();
		service.shutdown();
		return savedEmployees;
	}
	
	/*
	 * Exception handling is possible using Callable
	 */
	public List<Employee> usingCallable(MultipartFile file) throws InterruptedException {
	    // Create an ExecutorService with a fixed thread pool of size 10
	    ExecutorService service = Executors.newFixedThreadPool(10);
	    
	    List<Employee> savedEmployees = null;

	    try {
	        // Callable for parsing the CSV file
	        Callable<List<Employee>> parseTask = () -> {
	            try {
	                List<Employee> empList = csvParser.csvParser(file);
	                logger.info("CSV file parsed successfully.");
	                return empList;
	            } catch (Exception e) {
	                logger.error("Error in parsing the CSV file: " + e.getMessage());
	                throw new RuntimeException("Parsing failed", e);
	            }
	        };

	        // Submit the parsing task and get the parsed result
	        Future<List<Employee>> parseFuture = service.submit(parseTask);

	        try {
	            parsedList = parseFuture.get(); // Get the parsed list of employees
	        } catch (ExecutionException e) {
	            logger.error("ExecutionException while parsing the CSV file: " + e.getCause().getMessage());
	            throw new RuntimeException("Error during parsing task execution", e);
	        }

	        // Callable for saving the parsed employee list
	        Callable<List<Employee>> saveTask = () -> {
	            try {
	                List<Employee> savedEmpList = empService.saveEmployees(parsedList);
	                logger.info("Employees saved successfully.");
	                return savedEmpList;
	            } catch (Exception e) {
	                logger.error("Error in saving the employee list: " + e.getMessage());
	                throw new RuntimeException("Saving failed", e);
	            }
	        };

	        // Submit the saving task and get the saved employees
	        Future<List<Employee>> saveFuture = service.submit(saveTask);

	        try {
	            savedEmployees = saveFuture.get(); // Get the saved employees list
	        } catch (ExecutionException e) {
	            logger.error("ExecutionException while saving employees: " + e.getCause().getMessage());
	            throw new RuntimeException("Error during saving task execution", e);
	        }

	    } catch (InterruptedException e) {
	        logger.error("InterruptedException: " + e.getMessage());
	        Thread.currentThread().interrupt(); // Restore interrupted state
	        throw e;
	    } finally {
	        // Always ensure the ExecutorService is shut down
	        service.shutdown();
	    }

	    return savedEmployees;
	}

	
}
