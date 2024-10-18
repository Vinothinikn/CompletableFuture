package com.completablefuture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.completablefuture.entity.EmployeeJson;

@Repository
public interface EmployeeJsonRepository extends JpaRepository<EmployeeJson, String> {

}
