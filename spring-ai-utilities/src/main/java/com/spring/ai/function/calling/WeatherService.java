package com.spring.ai.function.calling;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {
	
	private final RestClient restClient = RestClient.builder().build();
	private final ObjectMapper objectMapper = new ObjectMapper();

	public WeatherResponse getWeather(String city) {
		try {
			String url = "https://wttr.in/" + city + "?format=j1";

			String response = restClient.get().uri(url).retrieve().body(String.class);
			JsonNode root = objectMapper.readTree(response);

			JsonNode currentCondition = root.path("current_condition").get(0);

			Double temperature = currentCondition.path("temp_C").asDouble();

			String condition = currentCondition.path("weatherDesc").get(0).path("value").asText();

			return new WeatherResponse(city, temperature, condition);

		} catch (Exception e) {

			throw new RuntimeException("Failed to fetch weather data for " + city, e);
		}
	}
}
