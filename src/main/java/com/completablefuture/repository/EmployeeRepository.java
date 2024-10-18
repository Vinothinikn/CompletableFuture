package com.completablefuture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.completablefuture.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
