package com.spring.ai.embedding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SemanticSearchController {

	private final EmbeddingModel embeddingModel;

	public SemanticSearchController(EmbeddingModel embeddingModel) {

		this.embeddingModel = embeddingModel;
	}

	/**
	 * Sample document repository
	 */
	private final List<String> documents = List.of(

			"Java Developer with Spring Boot experience",

			"Spring Boot Microservices Architect",

			"Python Data Scientist with Machine Learning skills",

			"React Frontend Developer",

			"DevOps Engineer with Docker and Kubernetes",

			"AI Engineer using Ollama and Spring AI",

			"Java Technical Architect with Microservices",

			"Cloud Engineer using AWS and Kubernetes",

			"Backend Engineer using Java and PostgreSQL",

			"Full Stack Developer using React and Spring Boot");

	/**
	 * Semantic Search API
	 *
	 * Example: GET /semantic-search?query=Spring Boot Developer
	 */
	@GetMapping("/semantic-search")
	public List<SearchResult> semanticSearch(@RequestParam("query") String query) {

		float[] queryVector = embeddingModel.embed(query);

		List<SearchResult> results = new ArrayList<>();

		for (String document : documents) {

			float[] documentVector = embeddingModel.embed(document);

			double score = cosineSimilarity(queryVector, documentVector);

			results.add(new SearchResult(document, score));
		}

		return results.stream().sorted(Comparator.comparingDouble(SearchResult::score).reversed()).limit(5).toList();
	}

	/**
	 * Calculate Cosine Similarity
	 */
	private double cosineSimilarity(float[] vectorA, float[] vectorB) {

		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;

		for (int i = 0; i < vectorA.length; i++) {

			dotProduct += vectorA[i] * vectorB[i];

			normA += vectorA[i] * vectorA[i];

			normB += vectorB[i] * vectorB[i];
		}

		return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}

	/**
	 * Response DTO
	 */
	public record SearchResult(String document, double score) {
	}
}
