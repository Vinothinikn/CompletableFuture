package com.completablefuture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.completablefuture.entity.StockPriceReport;

@Repository
public interface StockPriceReportRepository extends JpaRepository<StockPriceReport, Long>{

}
