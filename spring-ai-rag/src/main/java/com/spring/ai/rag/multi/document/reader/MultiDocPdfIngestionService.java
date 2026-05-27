package com.spring.ai.rag.multi.document.reader;

import java.io.File;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class MultiDocPdfIngestionService {

	private final VectorStore vectorStore;

	public MultiDocPdfIngestionService(VectorStore vectorStore) {
		this.vectorStore = vectorStore;
	}

	public void ingest(File pdfFile) {

		FileSystemResource resource = new FileSystemResource(pdfFile);

		PagePdfDocumentReader reader = new PagePdfDocumentReader(resource);

		List<Document> pages = reader.get();

		TokenTextSplitter splitter = new TokenTextSplitter();

		List<Document> chunks = splitter.apply(pages);

		for (Document chunk : chunks) {

			chunk.getMetadata().put("source", pdfFile.getName());
		}

		System.out.println("Pages : " + pages.size());
		System.out.println("Chunks: " + chunks.size());

		vectorStore.add(chunks);
	}
}
