package com.completablefuture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.completablefuture.entity.WeatherReport;

@Repository
public interface WeatherReportRepository extends JpaRepository<WeatherReport, Long>{

}
