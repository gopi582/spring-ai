package com.spring.ai.rag.multi.document.reader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rag/v1")
public class MultiDocRagController {

	private final ChatClient chatClient;
	private final VectorStore vectorStore;

	public MultiDocRagController(@Qualifier("multiDocChatClient") ChatClient chatClient, VectorStore vectorStore) {

		this.chatClient = chatClient;
		this.vectorStore = vectorStore;
	}

	@GetMapping("/search")
	public List<Map<String, Object>> search(@RequestParam("query") String query) {

		List<Document> docs = vectorStore.similaritySearch(SearchRequest.builder().query(query).topK(5).build());

		return docs.stream().map(doc -> Map.of("source", doc.getMetadata().getOrDefault("source", "unknown"),

				"content", doc.getText())).toList();
	}

	@GetMapping("/ask")
	public String ask(@RequestParam("question") String question) {

		List<Document> docs = vectorStore.similaritySearch(SearchRequest.builder().query(question).topK(5).build());

		String context = docs.stream().map(Document::getText).collect(Collectors.joining("\n"));

		return chatClient.prompt().user("""
				Answer only from the given context.

				Context:
				%s

				Question:
				%s
				""".formatted(context, question)).call().content();
	}
}
