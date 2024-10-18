package com.completablefuture.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.completablefuture.dto.MultipleFutureDTO;
import com.completablefuture.dto.StatusCode;
import com.completablefuture.entity.Employee;
import com.completablefuture.entity.EmployeeJson;
import com.completablefuture.entity.NewsReport;
import com.completablefuture.entity.StockPriceReport;
import com.completablefuture.entity.WeatherReport;
import com.completablefuture.service.BeforeJava8Example;
import com.completablefuture.service.CombineMultipleFutureAllOf;
import com.completablefuture.service.CombineMultipleFutureAnyOf;
import com.completablefuture.service.CompletableFutureRunAsync;
import com.completablefuture.service.CompletableFutureSupplyAsync;
import com.completablefuture.service.CompletableFutureThenApplyAcceptAsync;
import com.completablefuture.service.ComposeCombineCompletableFuture;


@RestController
@RequestMapping("/completablefuture")
public class MainController {
	
	@Autowired
	BeforeJava8Example beforeJava8;
	
	@Autowired
	CompletableFutureRunAsync runAsyncService;
	
	@Autowired
	CompletableFutureSupplyAsync supplyAsyncService;
	
	@Autowired
	CompletableFutureThenApplyAcceptAsync thenService;
	
	@Autowired
	ComposeCombineCompletableFuture combineService;
	
	@Autowired
	CombineMultipleFutureAllOf multipleFuture;
	
	@Autowired
	CombineMultipleFutureAnyOf multipleFutureAnyOf;
	
	MultipleFutureDTO dto = null;

	@PostMapping("/usingfuture")
	public ResponseEntity<?> getAllDataUsingFuture(@RequestParam("file") MultipartFile file) throws InterruptedException, ExecutionException{
	
		if(file.isEmpty()) {
			StatusCode status = new StatusCode(601, "File not found");
			return new ResponseEntity<StatusCode>(status, HttpStatus.NOT_FOUND);
		}	
	 List<Employee> empList =	beforeJava8.onlyFuture(file);
	  
	  if(empList.isEmpty()) {
		  StatusCode statusCode = new StatusCode(602, "Employee Save Failed");
		  return new ResponseEntity<StatusCode>(statusCode, HttpStatus.NOT_FOUND);
	  }
	  else {
		  StatusCode statusCode = new StatusCode(603, "Employee Saved Successfully");
		  return new ResponseEntity<>(statusCode, HttpStatus.OK);
	  }
	}
	
	@PostMapping("/usingcallable")
	public ResponseEntity<?> getAllDataUsingCallable(@RequestParam("file") MultipartFile file) throws InterruptedException, ExecutionException{
	
		if(file.isEmpty()) {
			StatusCode status = new StatusCode(601, "File not found");
			return new ResponseEntity<StatusCode>(status, HttpStatus.NOT_FOUND);
		}	
	 List<Employee> empList =	beforeJava8.usingCallable(file);
	  
	  if(empList.isEmpty()) {
		  StatusCode statusCode = new StatusCode(602, "Employee Save Failed");
		  return new ResponseEntity<StatusCode>(statusCode, HttpStatus.NOT_FOUND);
	  }
	  else {
		  StatusCode statusCode = new StatusCode(603, "Employee Saved Successfully");
		  return new ResponseEntity<>(statusCode, HttpStatus.OK);
	  }
	}
	
	
	@PostMapping("/runasync")
	public void saveEmployeeUsingRunAsync(@RequestParam("file") MultipartFile file) throws IllegalStateException, InterruptedException, ExecutionException, IOException{
		//runAsyncService.runSyncExample(file);
		runAsyncService.runSyncExampleWithExecutor(file);
		//runAsyncService.saveAllEmployeeCSVDetailsToDB(file);
		
	}
	
	
	@PostMapping("/supplyAsync")
	public ResponseEntity<?> saveEmployeeUsingSupplyAsync(@RequestParam("file") MultipartFile file) throws InterruptedException, ExecutionException{
		
		List<Employee> empList = supplyAsyncService.supplyAsyncExample(file);
		if(empList.isEmpty()) {
			 StatusCode statusCode = new StatusCode(602, "Employee Save Failed");
			  return new ResponseEntity<StatusCode>(statusCode, HttpStatus.NOT_FOUND);
		}else {
			StatusCode statusCode = new StatusCode(603, "Employee Saved Successfully");
			  return new ResponseEntity<>(statusCode, HttpStatus.OK);
		}
	}
	
	@PostMapping("/thenAsync")
	public ResponseEntity<?> saveEmployeeUsingThenAsync() throws InterruptedException, ExecutionException{
		Void nVoid = thenService.getAllEmployeesFromDBUsingAsyncAndExecutor();
		return null;
	}
	
	@GetMapping("/thenCompose/{id}")
	public ResponseEntity<Integer> dependentFuture(@PathVariable("id") String id) throws InterruptedException, ExecutionException {
		// 2 dependent completableFuture
		CompletableFuture<Integer> finalResult = combineService.getEmployeeById(id)
												.thenCompose(emp -> combineService.getRating(emp));
		System.out.println(finalResult.get());
		return new ResponseEntity<Integer>(finalResult.get(), HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/thenCombine")
	public String independentFutureCombine() throws InterruptedException, ExecutionException {
	  CompletableFuture<String> result=	combineService.getEmpCountBasedOnGender().thenCombine(combineService.getEmail(), (genderCount, emails)->{
			return "genderCount: " +genderCount.toString() +"emails: "+emails.toString();
		});
	  return result.get();
	}
	
	@PostMapping("/multiplefuture/allOf")
	public MultipleFutureDTO mulitpleIndependentFutureUsingAllOf(@RequestParam("file") MultipartFile[] file) throws InterruptedException, ExecutionException {
		
		CompletableFuture<List<WeatherReport>> weatherList =  multipleFuture.saveWeatherReport(file[0]);
		CompletableFuture<List<NewsReport>> newsReportList = multipleFuture.saveNewsReport(file[1]);
		CompletableFuture<List<StockPriceReport>> stockPriceList = multipleFuture.saveStockPriceReport(file[2]);
		
		 CompletableFuture<MultipleFutureDTO> dtoClass = CompletableFuture.allOf(weatherList, newsReportList, stockPriceList)
			        .thenApplyAsync(v -> {
			            try {
			                // Extract the results after all futures are completed
			                List<WeatherReport> weathers  = weatherList.get();
			                List<StockPriceReport> stocks = stockPriceList.get();
			                List<NewsReport> news = newsReportList.get();

			                // Create the DTO with the results
			                MultipleFutureDTO dto = new MultipleFutureDTO(weathers, stocks, news);
			                return dto; // Return the DTO
			            } catch (InterruptedException | ExecutionException e) {
			                throw new RuntimeException("Error occurred while combining futures", e);
			            }
			        });
		return dtoClass.get();
	}
	
	@PostMapping("/multiplefuture/anyOf")
	public CompletableFuture<Object> mulitpleIndependentFutureUsingAnyOf(@RequestParam("file") MultipartFile[] file) {
		CompletableFuture<List<EmployeeJson>> employeesFuture =  multipleFutureAnyOf.getEmployeeDataFromJson(file[0]);
		CompletableFuture<List<WeatherReport>> weatherReportFuture = multipleFutureAnyOf.getWeatherReportFromJson(file[1]);
		return CompletableFuture.anyOf(employeesFuture, weatherReportFuture).thenApplyAsync((data)->{
			return data;
		});
	}
}
