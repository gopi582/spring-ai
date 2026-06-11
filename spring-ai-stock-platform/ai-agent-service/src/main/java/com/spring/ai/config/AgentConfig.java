package com.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {
	@Bean
	ChatClient chatClient(

			ChatClient.Builder builder,

			SyncMcpToolCallbackProvider toolProvider) {

		return builder

				.defaultSystem("""
						You are a stock AI assistant.

						IMPORTANT:
						- ALWAYS use tools for stock prices
						- NEVER guess stock prices
						""")

				.defaultToolCallbacks(toolProvider.getToolCallbacks())

				.build();
	}
}