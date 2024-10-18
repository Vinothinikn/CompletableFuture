package com.completablefuture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.completablefuture.entity.NewsReport;

@Repository
public interface NewsReportRepository extends JpaRepository<NewsReport, Long>{

}
