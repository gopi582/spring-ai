package com.spring.ai.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ai.service.PdfIngestionService;

@RestController
@RequestMapping("/documents")
public class DocumentController {

	private final PdfIngestionService service;

	public DocumentController(PdfIngestionService service) {
		this.service = service;
	}

	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) throws Exception {

		return service.ingest(file);
	}
}