package com.completablefuture.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.completablefuture.entity.Employee;
import com.completablefuture.parserservice.ParserService;
import com.opencsv.exceptions.CsvException;

@Service
public class CompletableFutureSupplyAsync {
	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	ParserService parserService;
	
	public List<Employee> supplyAsyncExample(MultipartFile file) throws InterruptedException, ExecutionException {
		CompletableFuture<List<Employee>> employeeFuture = CompletableFuture.supplyAsync(()->{
			List<Employee> parsedList = new ArrayList<>();
			try {
				parsedList = parserService.csvParser(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CsvException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return empService.saveEmployees(parsedList);
		});
		
		return employeeFuture.get();
	}

}
