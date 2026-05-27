package com.spring.ai.rag.pdf.extraction;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/rag")
public class RagController {

	private final ChatClient chatClient;
	private final VectorStore vectorStore;

	public RagController(@Qualifier("defaultChatClient") ChatClient chatClient, VectorStore vectorStore) {

		this.chatClient = chatClient;
		this.vectorStore = vectorStore;
	}

	@GetMapping("/ask")
	public String ask(@RequestParam("question") String question) {

		return chatClient.prompt().user(question).call().content();
	}

	@GetMapping("/search")
	public String search(@RequestParam("query") String query) {

		List<Document> docs = vectorStore.similaritySearch(SearchRequest.builder().query(query).topK(5).build());

		return docs.stream().map(Document::getText).collect(Collectors.joining("\n\n====================\n\n"));
	}

	@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> stream(@RequestParam("question") String question) {

		return chatClient.prompt().user(question).stream().content();
	}
}