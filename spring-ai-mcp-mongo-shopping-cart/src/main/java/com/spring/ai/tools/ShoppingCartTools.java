package com.spring.ai.tools;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.spring.ai.model.Product;
import com.spring.ai.service.CartService;

@Component
public class ShoppingCartTools {

	private final CartService cartService;

	public ShoppingCartTools(CartService cartService) {
		this.cartService = cartService;
	}

	@Tool(description = "Add a product to shopping cart")
	public Product addToCart(String productName, double price, int quantity) {

		Product product = new Product(productName, price, quantity);

		return cartService.addProduct(product);
	}

	@Tool(description = "View all cart items")
	public List<Product> viewCart() {
		return cartService.getAllProducts();
	}

	@Tool(description = "Remove product from cart")
	public String removeProduct(String id) {
		return cartService.removeProduct(id);
	}

	@Tool(description = "Calculate total cart amount")
	public String checkout() {

		double total = cartService.checkout();

		return "Total Amount : " + total;
	}
}