package com.completablefuture.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class StockPriceReport {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String symbol;       // Stock symbol (e.g., "AAPL", "GOOGL")
	    private double currentPrice; // Current stock price
	    private double openPrice;    // Opening stock price for the day
	    private double closePrice;   // Closing price of the stock
	    private double dayHigh;      // Highest price during the day
	    private double dayLow;       // Lowest price during the day
	    private long volume;         // Volume of shares traded
	    private String reportTime;   // Time of the stock report

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getSymbol() {
	        return symbol;
	    }

	    public void setSymbol(String symbol) {
	        this.symbol = symbol;
	    }

	    public double getCurrentPrice() {
	        return currentPrice;
	    }

	    public void setCurrentPrice(double currentPrice) {
	        this.currentPrice = currentPrice;
	    }

	    public double getOpenPrice() {
	        return openPrice;
	    }

	    public void setOpenPrice(double openPrice) {
	        this.openPrice = openPrice;
	    }

	    public double getClosePrice() {
	        return closePrice;
	    }

	    public void setClosePrice(double closePrice) {
	        this.closePrice = closePrice;
	    }

	    public double getDayHigh() {
	        return dayHigh;
	    }

	    public void setDayHigh(double dayHigh) {
	        this.dayHigh = dayHigh;
	    }

	    public double getDayLow() {
	        return dayLow;
	    }

	    public void setDayLow(double dayLow) {
	        this.dayLow = dayLow;
	    }

	    public long getVolume() {
	        return volume;
	    }

	    public void setVolume(long volume) {
	        this.volume = volume;
	    }

	    public String getReportTime() {
	        return reportTime;
	    }

	    public void setReportTime(String reportTime) {
	        this.reportTime = reportTime;
	    }
}
