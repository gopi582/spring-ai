package com.spring.ai.prompts;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleBasedPromptController {

	private final ChatClient chatClient;

	public RoleBasedPromptController(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	@GetMapping("/java-architect")
	public String javaArchitect(@RequestParam("question") String question) {

		return chatClient.prompt().system("""
				You are a Senior Java Technical Architect.

				Responsibilities:
				- Design scalable systems
				- Explain architecture clearly
				- Provide best practices
				- Use real-world examples
				""").user(question).call().content();
	}

	@GetMapping("/interviewer")
	public String interviewer(@RequestParam("topic") String topic) {

		return chatClient.prompt().system("""
				You are an experienced Java interviewer.

				Ask interview questions from the given topic.
				Start with basic questions and gradually increase difficulty.
				""").user(topic).call().content();
	}

	@GetMapping("/teacher")
	public String teacher(@RequestParam("topic") String topic) {

		return chatClient.prompt().system("""
				You are a professional teacher.

				Explain concepts in a simple beginner-friendly way.
				Include examples wherever possible.
				""").user("Explain " + topic).call().content();
	}

	@GetMapping("/hr")
	public String hr(@RequestParam("question") String question) {

		return chatClient.prompt().system("""
				You are an HR Manager.

				Provide professional HR guidance.
				Keep answers concise and practical.
				""").user(question).call().content();
	}

	@GetMapping("/code-reviewer")
	public String codeReviewer(@RequestParam("code") String code) {

		return chatClient.prompt().system("""
				You are a Senior Code Reviewer.

				Review the code and provide:
				1. Issues
				2. Improvements
				3. Best Practices
				4. Optimized version suggestions
				""").user(code).call().content();
	}
	
	@GetMapping("/dynamic-role")
	public String ask(
	        @RequestParam("role") String role,
	        @RequestParam("question") String question) {

	    return chatClient.prompt()
	            .system("You are a " + role)
	            .user(question)
	            .call()
	            .content();
	}
}
