package com.spring.ai.advisor;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

	private final ChatClient chatClient;

	public ChatController(ChatClient.Builder builder, LoggingAdvisor loggingAdvisor) {

		this.chatClient = builder.defaultAdvisors(loggingAdvisor).build();
	}

	@GetMapping("/ask")
	public String ask(@RequestParam("message") String message) {

		return chatClient.prompt().user(message).call().content();
	}
}