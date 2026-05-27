package com.spring.ai.evaluation;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluationController {

	private final ChatClient chatClient;

	public EvaluationController(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	@GetMapping("/evaluation")
	public Map<String, Object> evaluate(@RequestParam("question") String question) {

		String answer = chatClient.prompt().user(question).call().content();

		boolean passed = answer != null && answer.length() > 20;

		Map<String, Object> result = new LinkedHashMap<>();

		result.put("question", question);
		result.put("answer", answer);
		result.put("evaluation", passed ? "PASS" : "FAIL");

		return result;
	}
}
