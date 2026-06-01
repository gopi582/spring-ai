package com.spring.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class ShoppingTools {

	@Tool(description = "Add product into shopping cart")
	public String addToCart(String productName) {

		return productName + " added successfully into cart";
	}

	@Tool(description = "Remove product from shopping cart")
	public String removeFromCart(String productName) {

		return productName + " removed from cart";
	}
}
