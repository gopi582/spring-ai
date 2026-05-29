package com.spring.ai.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ai.model.Product;
import com.spring.ai.service.CartService;

//@RestController
//@RequestMapping("/cart")
public class CartController {

	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping("/add")
	public Product addProduct(@RequestBody Product product) {
		return cartService.addProduct(product);
	}

	@GetMapping("/view")
	public List<Product> viewCart() {
		return cartService.getAllProducts();
	}

	@DeleteMapping("/remove/{id}")
	public String removeProduct(@PathVariable String id) {
		return cartService.removeProduct(id);
	}

	@GetMapping("/checkout")
	public String checkout() {

		double total = cartService.checkout();

		return "Total Amount : " + total;
	}
}
