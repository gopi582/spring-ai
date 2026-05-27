package com.spring.ai.rag.pdf.extraction;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

	@Bean("defaultChatClient")
	public ChatClient chatClient(ChatClient.Builder builder, VectorStore vectorStore) {

		QuestionAnswerAdvisor advisor = QuestionAnswerAdvisor.builder(vectorStore)
				.searchRequest(SearchRequest.builder().topK(10).build()).build();

		return builder.defaultAdvisors(advisor).build();
	}
}