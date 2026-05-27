package com.spring.ai.function.calling;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class WeatherTools {
	private final WeatherService weatherService;

	public WeatherTools(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@Tool(description = "Get current weather information by city")
	public WeatherResponse getWeather(String city) {

		return weatherService.getWeather(city);
	}
}
