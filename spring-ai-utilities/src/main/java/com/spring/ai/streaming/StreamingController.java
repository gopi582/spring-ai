package com.spring.ai.streaming;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class StreamingController {

	private final ChatClient chatClient;

	public StreamingController(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	/**
	 * ✅ STREAMING (FIXED)
	 */
	@GetMapping(value = "/stream", produces = "text/plain;charset=UTF-8")
	public Flux<String> stream(@RequestParam("model") String model, @RequestParam("message") String message) {

		ChatOptions options = ChatOptions.builder().model(model).build();

		return chatClient.prompt().user(message).options(options).stream().content();
	}
	
	 /**
     * 2. System Prompt Streaming
     */
    @GetMapping(value = "/system", produces = "text/plain;charset=UTF-8")
    public Flux<String> systemPrompt(
            @RequestParam("message") String message) {

        return chatClient.prompt()
                .system("""
                        You are a Senior Java Architect.
                        Give concise answers.
                        """)
                .user(message)
                .stream()
                .content();
    }

	/**
	 * ✅ NORMAL CHAT (FIXED)
	 */
	@GetMapping("/chat")
	public String chat(@RequestParam("model") String model, @RequestParam("message") String message) {

		ChatOptions options = ChatOptions.builder().model(model).build();

		return chatClient.prompt().user(message).options(options).call().content();
	}

	/**
	 * ✅ CODE GENERATION
	 */
	@GetMapping("/code")
	public String code(@RequestParam("model") String model, @RequestParam("prompt") String prompt) {

		ChatOptions options = ChatOptions.builder().model(model).build();

		return chatClient.prompt().user("Generate Java code for: " + prompt).options(options).call().content();
	}

	/**
	 * ✅ SUMMARY
	 */
	@GetMapping("/summary")
	public String summary(@RequestParam("model") String model, @RequestParam("text") String text) {

		ChatOptions options = ChatOptions.builder().model(model).build();

		return chatClient.prompt().user("Summarize: " + text).options(options).call().content();
	}

	/**
	 * 6. JSON Output Streaming
	 */
	@GetMapping(value = "/json", produces = "text/plain;charset=UTF-8")
	public Flux<String> json(@RequestParam("topic") String topic) {

		return chatClient.prompt().user("""
				Generate JSON response for:
				%s
				""".formatted(topic)).stream().content();
	}

	/**
	 * 8. Translation Streaming
	 */
	@GetMapping(value = "/translate", produces = "text/plain;charset=UTF-8")
	public Flux<String> translate(@RequestParam("text") String text) {

		return chatClient.prompt().user("""
				Translate to Telugu:
				%s
				""".formatted(text)).stream().content();
	}

	/**
	 * 9. Interview Questions Streaming
	 */
	@GetMapping(value = "/interview", produces = "text/plain;charset=UTF-8")
	public Flux<String> interview(@RequestParam("topic") String topic) {

		return chatClient.prompt().user("""
				Generate 10 interview questions on:
				%s
				""".formatted(topic)).stream().content();
	}

	/**
	 * 10. Q&A Streaming
	 */
	@GetMapping(value = "/qa", produces = "text/plain;charset=UTF-8")
	public Flux<String> qa(@RequestParam("question") String question) {

		return chatClient.prompt().user(question).stream().content();
	}
}