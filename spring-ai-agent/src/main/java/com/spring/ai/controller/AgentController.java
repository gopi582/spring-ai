package com.spring.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ai.tools.ShoppingTools;

import reactor.core.publisher.Flux;

@RestController
public class AgentController {

	private final ChatClient chatClient;
	private final ShoppingTools shoppingTools;

	public AgentController(ChatClient.Builder builder, ShoppingTools shoppingTools) {

		this.chatClient = builder.build();
		this.shoppingTools = shoppingTools;
	}

	@GetMapping("/agent")
	public String agent(@RequestParam("message") String message) {

		return chatClient.prompt().system("""
				    You are a shopping cart assistant.
				    Use tools whenever needed.
				""").user(message)

				// Register AI tools
				.tools(shoppingTools)

				.call().content();
	}

	@GetMapping("/stream")
	public Flux<String> stream(@RequestParam("message") String message) {

		return chatClient.prompt().user(message).tools(shoppingTools).stream().content();
	}
}