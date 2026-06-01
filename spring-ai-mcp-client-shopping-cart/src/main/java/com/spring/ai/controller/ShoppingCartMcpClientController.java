package com.spring.ai.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ai.dto.ProductRequest;
import com.spring.ai.service.ShoppingCartMcpClientService;

@RestController
@RequestMapping("/mcp/client")
public class ShoppingCartMcpClientController {

	private final ShoppingCartMcpClientService service;

	public ShoppingCartMcpClientController(ShoppingCartMcpClientService service) {

		this.service = service;
	}

	@GetMapping("/tools")
	public Object tools() {

		return service.getTools();
	}

	@PostMapping("/cart/add")
	public Object addToCart(@RequestBody ProductRequest request) throws Exception {

		return service.addToCart(request.getProductName(), request.getPrice(), request.getQuantity());
	}

	@GetMapping("/cart")
	public Object viewCart() throws Exception {

		return service.viewCart();
	}

	@DeleteMapping("/cart/{id}")
	public Object removeProduct(@PathVariable("id") String id) throws Exception {

		return service.removeProduct(id);
	}

	@PostMapping("/cart/checkout")
	public Object checkout() throws Exception {

		return service.checkout();
	}
}