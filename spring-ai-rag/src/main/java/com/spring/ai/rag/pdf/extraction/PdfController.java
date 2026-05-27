package com.spring.ai.rag.pdf.extraction;

import java.io.File;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pdf")
public class PdfController {

	private final PdfIngestionService service;

	public PdfController(PdfIngestionService service) {
		this.service = service;
	}

	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) throws Exception {

		File temp = File.createTempFile("upload", ".pdf");

		file.transferTo(temp);

		service.ingest(temp);

		return "PDF Uploaded Successfully";
	}
}