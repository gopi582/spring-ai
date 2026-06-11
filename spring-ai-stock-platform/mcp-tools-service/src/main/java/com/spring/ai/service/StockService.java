package com.spring.ai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.ai.response.StockPriceResponse;

@Service
public class StockService {

    private final WebClient webClient;

    @Value("${stock.api.key}")
    private String apiKey;

    public StockService(WebClient.Builder builder) {

        this.webClient = builder
                .baseUrl("https://api.twelvedata.com")
                .build();
    }

    @Cacheable("stock-price")
    public String getPrice(String symbol) {
    	System.out.println("Calling TwelveData API for: " + symbol);
        StockPriceResponse response =
                webClient.get()

                        .uri(uriBuilder ->
                                uriBuilder.path("/price")
                                        .queryParam("symbol", symbol)
                                        .queryParam("apikey", apiKey)
                                        .build())

                        .retrieve()

                        .bodyToMono(StockPriceResponse.class)

                        .block();
        System.out.println("API Response: " + response);
        return symbol + " stock price is " + response.getPrice() + " USD";
    }
}