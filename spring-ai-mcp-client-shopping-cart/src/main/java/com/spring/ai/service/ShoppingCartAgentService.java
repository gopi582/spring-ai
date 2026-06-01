package com.spring.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartAgentService {

	private final ChatClient.Builder builder;

	private final ToolCallbackProvider tools;

	public ShoppingCartAgentService(ChatClient.Builder builder, ToolCallbackProvider tools) {

		this.builder = builder;
		this.tools = tools;
	}

	public String chat(String prompt) {

		ChatClient chatClient = builder.build();

		return chatClient

				.prompt()

				.system("""
						You are a shopping cart AI assistant.

						Always use available tools for:
						- adding products
						- viewing cart
						- removing products
						- checkout

						If user asks multiple tasks,
						execute all tasks step-by-step.
						""")

				.user(prompt)

				.toolCallbacks(tools.getToolCallbacks())

				.call()

				.content();
	}
}