package com.spring.ai.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;

import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;

import org.springframework.stereotype.Component;

@Component
public class LoggingAdvisor implements CallAdvisor {

	private static final Logger log = LoggerFactory.getLogger(LoggingAdvisor.class);

	@Override
	public String getName() {
		return "logging-advisor";
	}

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public ChatClientResponse adviseCall(ChatClientRequest request, CallAdvisorChain chain) {

		log.info("Request: {}", request.prompt());

		ChatClientResponse response = chain.nextCall(request);

		log.info("Response: {}", response.chatResponse().getResult().getOutput().getText());

		return response;
	}
}