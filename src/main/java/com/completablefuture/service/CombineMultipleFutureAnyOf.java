package com.completablefuture.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.completablefuture.entity.EmployeeJson;
import com.completablefuture.entity.WeatherReport;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CombineMultipleFutureAnyOf {

	public CompletableFuture<List<EmployeeJson>> getEmployeeDataFromJson(MultipartFile file) {
		ObjectMapper mapper = new ObjectMapper();
		return CompletableFuture.supplyAsync(()->{
			try {
				return mapper.readValue(file.getInputStream(), new TypeReference<List<EmployeeJson>>() {
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
			return null;
		});
		
	}
	
	public CompletableFuture<List<WeatherReport>> getWeatherReportFromJson(MultipartFile file) {
		ObjectMapper mapper = new ObjectMapper();
		return CompletableFuture.supplyAsync(()->{
			try {
				return mapper.readValue(file.getInputStream(), new TypeReference<List<WeatherReport>>() {
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
			return null;
		});
	}
}
