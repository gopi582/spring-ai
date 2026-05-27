package com.spring.ai.function.calling;

public record WeatherResponse(
        String city,
        Double temperature,
        String condition) {
}
