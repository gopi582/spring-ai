package com.spring.ai.function.calling;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunctionCallingController {
	private final FunctionCallingService service;

	public FunctionCallingController(FunctionCallingService service) {

		this.service = service;
	}

	@GetMapping("/function")
	public String ask(@RequestParam("question") String question) {
		return service.ask(question);
	}

}
