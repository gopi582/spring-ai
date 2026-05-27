package com.spring.ai.document.reader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class DocumentAiService {

	private final ChatClient chatClient;

	public DocumentAiService(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	// =====================================================
	// PDF
	// =====================================================

	public String pdfSummary() {
		return askAi(readPdf(), "Summarize this PDF document in simple language.");
	}

	public String pdfKeywords() {
		return askAi(readPdf(), "Extract important keywords from this PDF document.");
	}

	public String pdfQuestions() {
		return askAi(readPdf(), """
				Generate 10 interview questions with answers
				based on this PDF document.
				""");
	}

	public String pdfEntities() {
		return askAi(readPdf(), """
				Extract all important entities from this PDF.

				Include:
				- Person Names
				- Organizations
				- Locations
				- Technologies
				- Skills
				""");
	}

	// =====================================================
	// DOCX
	// =====================================================

	public String docxSummary() {
		return askAi(readDocx(), "Summarize this DOCX document.");
	}

	public String docxKeywords() {
		return askAi(readDocx(), "Extract important keywords from this DOCX document.");
	}

	// =====================================================
	// CSV
	// =====================================================

	public String csvAnalysis() {
		return askAi(readCsv(), """
				Analyze this CSV data.

				Explain:
				- Columns
				- Number of records
				- Important observations
				""");
	}

	public String csvInsights() {
		return askAi(readCsv(), """
				Generate business insights from this CSV data.

				Include:
				- Trends
				- Highest values
				- Lowest values
				- Recommendations
				""");
	}

	// =====================================================
	// JSON
	// =====================================================

	public String jsonAnalysis() {
		return askAi(readJson(), """
				Analyze this JSON document.

				Explain:
				- Structure
				- Important fields
				- Key information
				""");
	}

	// =====================================================
	// Helper Methods
	// =====================================================

	private String askAi(String documentContent, String instruction) {

		return chatClient.prompt().user("""
				%s

				Document Content:

				%s
				""".formatted(instruction, documentContent)).call().content();
	}

	private String readPdf() {

		ClassPathResource resource = new ClassPathResource("documents/company-policy.pdf");

		PagePdfDocumentReader reader = new PagePdfDocumentReader(resource);

		return reader.get().stream().map(Document::getText).collect(Collectors.joining("\n"));
	}

	private String readDocx() {

		try (InputStream is = new ClassPathResource("documents/company-policy.docx").getInputStream();

				XWPFDocument document = new XWPFDocument(is)) {

			return document.getParagraphs().stream().map(p -> p.getText()).collect(Collectors.joining("\n"));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String readCsv() {
		return readTextFile("documents/employees.csv");
	}

	private String readJson() {
		return readTextFile("documents/employee.json");
	}

	private String readTextFile(String fileName) {

		try (InputStream is = new ClassPathResource(fileName).getInputStream()) {

			return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
					.collect(Collectors.joining("\n"));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}