package com.spring.ai.rag.multi.document.reader;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiMultiDocConfig {

	@Bean("multiDocChatClient")
	ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}
}