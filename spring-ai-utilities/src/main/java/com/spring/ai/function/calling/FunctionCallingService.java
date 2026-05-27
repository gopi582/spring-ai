package com.spring.ai.function.calling;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class FunctionCallingService {
	private final ChatClient chatClient;
	private final WeatherTools weatherTools;

	public FunctionCallingService(ChatClient.Builder builder, WeatherTools weatherTools) {

		this.chatClient = builder.build();
		this.weatherTools = weatherTools;
	}

	public String ask(String question) {

		return chatClient.prompt().user(question).tools(weatherTools).call().content();
	}
}
