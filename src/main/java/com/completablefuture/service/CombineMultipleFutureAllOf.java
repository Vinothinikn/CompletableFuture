package com.completablefuture.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.completablefuture.entity.NewsReport;
import com.completablefuture.entity.StockPriceReport;
import com.completablefuture.entity.WeatherReport;
import com.completablefuture.repository.NewsReportRepository;
import com.completablefuture.repository.StockPriceReportRepository;
import com.completablefuture.repository.WeatherReportRepository;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CombineMultipleFutureAllOf {

	@Autowired
	WeatherReportRepository weatherRepo;
	
	@Autowired
	NewsReportRepository newsReportRepo;
	
	@Autowired
	StockPriceReportRepository stockRepo;
	
	List<WeatherReport> weatherList = new ArrayList<>();
	List<NewsReport> newsReportList = new ArrayList<>();
	List<StockPriceReport> stockPriceList = new ArrayList<>();
	
	public CompletableFuture<List<WeatherReport>> saveWeatherReport(MultipartFile file) {
		ObjectMapper mapper = new ObjectMapper();
		
		return CompletableFuture.supplyAsync(()->{
			System.out.println("Thread for saveWeatherReport: "+Thread.currentThread().getName());
			try {
				weatherList = mapper.readValue(file.getInputStream(), new TypeReference<List<WeatherReport>>() {
					
				});
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
			return weatherList;
			
		}).thenApplyAsync((emp)->{
			return weatherRepo.saveAll(emp);
		});
	}
	
	public CompletableFuture<List<NewsReport>> saveNewsReport(MultipartFile file) {
		ObjectMapper mapper = new ObjectMapper();
		
		return CompletableFuture.supplyAsync(()->{
			System.out.println("Thread for saveNewsReport: "+Thread.currentThread().getName());
			try {
				newsReportList = mapper.readValue(file.getInputStream(), new TypeReference<List<NewsReport>>() {
					
				});
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
			return newsReportList;
			
		}).thenApplyAsync((emp)->{
			return newsReportRepo.saveAll(emp);
		});
	}
	
	public CompletableFuture<List<StockPriceReport>> saveStockPriceReport(MultipartFile file) {
		ObjectMapper mapper = new ObjectMapper();
		
		return CompletableFuture.supplyAsync(()->{
			System.out.println("Thread for saveStockPriceReport: "+Thread.currentThread().getName());
			try {
				stockPriceList = mapper.readValue(file.getInputStream(), new TypeReference<List<StockPriceReport>>() {
					
				});
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
			return stockPriceList;
			
		}).thenApplyAsync((emp)->{
			return stockRepo.saveAll(emp);
		});
	}
}
