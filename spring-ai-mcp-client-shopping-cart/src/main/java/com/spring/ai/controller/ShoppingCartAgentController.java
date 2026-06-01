package com.spring.ai.controller;

import org.springframework.web.bind.annotation.*;
import com.spring.ai.service.ShoppingCartAgentService;

@RestController
@RequestMapping("/agent")
public class ShoppingCartAgentController {
	private final ShoppingCartAgentService service;

	public ShoppingCartAgentController(ShoppingCartAgentService service) {
		this.service = service;
	}

	@PostMapping("/chat")
	public String chat(@RequestBody String prompt) {
		return service.chat(prompt);
	}
}