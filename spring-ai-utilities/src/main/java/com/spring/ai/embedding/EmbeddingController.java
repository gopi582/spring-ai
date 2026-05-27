package com.spring.ai.embedding;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmbeddingController {

	private final EmbeddingModel embeddingModel;

	public EmbeddingController(EmbeddingModel embeddingModel) {
		this.embeddingModel = embeddingModel;
	}

	/**
	 * Generate embedding for a single text
	 *
	 * Example: GET /embedding?text=Java Programming
	 */
	@GetMapping("/embedding")
	public float[] embedding(@RequestParam("text") String text) {

		return embeddingModel.embed(text);
	}

	/**
	 * Generate embeddings for multiple texts
	 *
	 * Example: GET /embeddings
	 */
	@GetMapping("/embeddings")
	public List<float[]> embeddings() {

		List<float[]> vectors = new ArrayList<>();

		vectors.add(embeddingModel.embed("Java Programming"));
		vectors.add(embeddingModel.embed("Spring Boot"));
		vectors.add(embeddingModel.embed("Microservices"));

		return vectors;
	}

	/**
	 * Compare semantic similarity
	 *
	 * Example: GET /similarity?text1=Java Developer&text2=Spring Boot Developer
	 */
	@GetMapping("/similarity")
	public double similarity(@RequestParam("text1") String text1, @RequestParam("text2") String text2) {

		float[] vector1 = embeddingModel.embed(text1);
		float[] vector2 = embeddingModel.embed(text2);

		return cosineSimilarity(vector1, vector2);
	}

	/**
	 * Get vector dimensions
	 *
	 * Example: GET /embedding-dimension?text=Java
	 */
	@GetMapping("/embedding-dimension")
	public String embeddingDimension(@RequestParam("text") String text) {

		float[] vector = embeddingModel.embed(text);

		return "Embedding Dimension: " + vector.length;
	}

	private double cosineSimilarity(float[] a, float[] b) {

		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;

		for (int i = 0; i < a.length; i++) {

			dotProduct += a[i] * b[i];

			normA += Math.pow(a[i], 2);

			normB += Math.pow(b[i], 2);
		}

		return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}
}