package com.spring.ai.evaluation;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eval")
public class LLMEvaluationController {

	private final ChatClient chatClient;

	public LLMEvaluationController(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	@GetMapping("/judge")
	public Map<String, String> judge(@RequestParam("question") String question) {

		String answer = chatClient.prompt().user(question).call().content();

		String evaluationPrompt = """
				Evaluate the following answer.

				Question:
				%s

				Answer:
				%s

				Return only:
				PASS
				or
				FAIL
				""".formatted(question, answer);

		String result = chatClient.prompt().user(evaluationPrompt).call().content();

		Map<String, String> response = new LinkedHashMap<>();

		response.put("question", question);
		response.put("answer", answer);
		response.put("evaluation", result);

		return response;
	}
}