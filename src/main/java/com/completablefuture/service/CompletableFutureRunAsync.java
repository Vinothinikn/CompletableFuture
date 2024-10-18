package com.completablefuture.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.completablefuture.dto.EmployeeJsonDTO;
import com.completablefuture.entity.Employee;
import com.completablefuture.entity.EmployeeJson;
import com.completablefuture.parserservice.ParserService;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvException;

@Service
public class CompletableFutureRunAsync {
	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	ParserService parserService;
	
	List<Employee> empList = new ArrayList<>();
	
	
	public Void runSyncExample(MultipartFile jsonFile) throws InterruptedException, ExecutionException, IllegalStateException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(()->{
			try {
				List<EmployeeJsonDTO>  employeeList = mapper.readValue(jsonFile.getInputStream(), new TypeReference<List<EmployeeJsonDTO>>() {
				});
				System.out.println("Thread name :"+Thread.currentThread().getName());
				System.out.println("No of Employees :"+employeeList.size());
				
				//Parsing the DTO to Entity
				List<EmployeeJson> jsonList = parserService.setEmployeeJSON(employeeList);
				
				// Save to DB;
				empService.saveJsonEmployees(jsonList);
				
			} catch (StreamReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return runAsyncFuture.get();
	}
	
public Void runSyncExampleWithExecutor(MultipartFile jsonFile) throws InterruptedException, ExecutionException, IllegalStateException, IOException {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		ObjectMapper mapper = new ObjectMapper();
		
		CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(()->{
			try {
				List<EmployeeJsonDTO>  employeeList = mapper.readValue(jsonFile.getInputStream(), new TypeReference<List<EmployeeJsonDTO>>() {
				});
				System.out.println("Thread name :"+Thread.currentThread().getName());
				System.out.println("No of Employees :"+employeeList.size());
				
				//Parsing the DTO to Entity
				List<EmployeeJson> jsonList = parserService.setEmployeeJSON(employeeList);
				
				// Save to DB;
				empService.saveJsonEmployees(jsonList);
				
			} catch (StreamReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, executor);
		return runAsyncFuture.get();
	}

	public void saveAllEmployeeCSVDetailsToDB(MultipartFile file) throws InterruptedException, ExecutionException {
		
		CompletableFuture<Void> runAsyncCSVFuture = CompletableFuture.runAsync(()->{
			try {
				 empList = parserService.csvParser(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CsvException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			empService.saveEmployees(empList);
		});
		
		runAsyncCSVFuture.get();
	}

}
