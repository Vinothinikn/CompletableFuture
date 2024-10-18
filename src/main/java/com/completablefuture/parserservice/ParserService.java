package com.completablefuture.parserservice;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.completablefuture.dto.EmployeeJsonDTO;
import com.completablefuture.entity.Employee;
import com.completablefuture.entity.EmployeeJson;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

@Component
public class ParserService {

	Logger logger = LoggerFactory.getLogger(ParserService.class);

	
	public List<Employee> csvParser(MultipartFile file) throws IOException, CsvException{
		List<Employee> empList = new ArrayList<>();
		try(CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))){
			List<String[]> records =  reader.readAll();
			for(String[] record : records) {
				 Employee employee = new Employee();
	                employee.setId(record[0]);
	                employee.setFullName(record[1]);
	                employee.setJobTitle(record[2]);
	                employee.setDepartment(record[3]);
	                employee.setBusinessUnit(record[4]);
	                employee.setGender(record[5]);
	                employee.setEthinicity(record[6]);
	                employee.setAge(record[7]);
	                employee.setHireDate(record[8]);
	                employee.setAnnualSalary(record[9]);
	                employee.setBonus(record[10]);
	                employee.setCountry(record[11]);
	                employee.setCity(record[12]);
	                employee.setExitDate(record[13]);
	                empList.add(employee);
			}
		}catch(Exception e) {
			logger.error("Error in parsing the CSV file " +e.getMessage());
		}
		return empList;
	}
	
	public List<EmployeeJson> setEmployeeJSON(List<EmployeeJsonDTO> dto){
		
		return dto.stream().map(d->new EmployeeJson(d.getEmployeeId(), d.getFirstName(), d.getLastName(), 
				d.getEmail(), d.getGender(), d.getNewJoiner(), 
				d.getLearningPending(), d.getSalary(), d.getRating()))
				.collect(Collectors.toList());
	}
}
