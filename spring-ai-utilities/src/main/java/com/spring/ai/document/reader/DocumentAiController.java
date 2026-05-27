package com.spring.ai.document.reader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ollama")
public class DocumentAiController {

	private final DocumentAiService service;

	public DocumentAiController(DocumentAiService service) {
		this.service = service;
	}

	// PDF APIs

	@GetMapping("/pdf-summary")
	public String pdfSummary() {
		return service.pdfSummary();
	}

	@GetMapping("/pdf-keywords")
	public String pdfKeywords() {
		return service.pdfKeywords();
	}

	@GetMapping("/pdf-questions")
	public String pdfQuestions() {
		return service.pdfQuestions();
	}

	@GetMapping("/pdf-entities")
	public String pdfEntities() {
		return service.pdfEntities();
	}

	// DOCX APIs

	@GetMapping("/docx-summary")
	public String docxSummary() {
		return service.docxSummary();
	}

	@GetMapping("/docx-keywords")
	public String docxKeywords() {
		return service.docxKeywords();
	}

	// CSV APIs

	@GetMapping("/csv-analysis")
	public String csvAnalysis() {
		return service.csvAnalysis();
	}

	@GetMapping("/csv-insights")
	public String csvInsights() {
		return service.csvInsights();
	}

	// JSON APIs

	@GetMapping("/json-analysis")
	public String jsonAnalysis() {
		return service.jsonAnalysis();
	}
}