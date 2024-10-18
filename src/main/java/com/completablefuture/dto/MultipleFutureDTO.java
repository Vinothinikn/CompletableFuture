package com.completablefuture.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.completablefuture.entity.NewsReport;
import com.completablefuture.entity.StockPriceReport;
import com.completablefuture.entity.WeatherReport;

@Component
public class MultipleFutureDTO {

	private List<WeatherReport> weatherList;
	private List<StockPriceReport> stockList;
	private List<NewsReport> newsList;
	
	public MultipleFutureDTO() {
	}

	public MultipleFutureDTO(List<WeatherReport> weatherList, List<StockPriceReport> stockList,
			List<NewsReport> newsList) {
		super();
		this.weatherList = weatherList;
		this.stockList = stockList;
		this.newsList = newsList;
	}

	public List<WeatherReport> getWeatherList() {
		return weatherList;
	}

	public void setWeatherList(List<WeatherReport> weatherList) {
		this.weatherList = weatherList;
	}

	public List<StockPriceReport> getStockList() {
		return stockList;
	}

	public void setStockList(List<StockPriceReport> stockList) {
		this.stockList = stockList;
	}

	public List<NewsReport> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<NewsReport> newsList) {
		this.newsList = newsList;
	}
	
	
}
