package com.spring.ai.rag.multi.document.reader;

import java.io.File;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pdf/v1")
public class MultiDocPdfController {

	private final MultiDocPdfIngestionService service;

	public MultiDocPdfController(MultiDocPdfIngestionService service) {
		this.service = service;
	}

	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) throws Exception {

		File temp = File.createTempFile("upload", ".pdf");

		file.transferTo(temp);

		service.ingest(temp);

		return "PDF uploaded successfully";
	}
}