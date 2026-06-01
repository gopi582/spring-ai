package com.spring.ai.service;

import java.util.Arrays;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartMcpClientService {

	private final ToolCallbackProvider toolCallbackProvider;

	public ShoppingCartMcpClientService(ToolCallbackProvider toolCallbackProvider) {

		this.toolCallbackProvider = toolCallbackProvider;
	}

	public ToolCallback[] getTools() {

		return toolCallbackProvider.getToolCallbacks();
	}

	private ToolCallback getTool(String toolName) {

		return Arrays.stream(toolCallbackProvider.getToolCallbacks())

				.peek(t -> System.err.println("AVAILABLE TOOL : " + t.getToolDefinition().name()))

				.filter(t -> t.getToolDefinition().name().endsWith(toolName))

				.findFirst()

				.orElseThrow(() -> new RuntimeException("Tool not found: " + toolName));
	}

	public Object addToCart(String productName, double price, int quantity) {

		ToolCallback tool = getTool("addToCart");

		String input = """
				{
				  "productName":"%s",
				  "price":%s,
				  "quantity":%s
				}
				""".formatted(productName, price, quantity);

		return tool.call(input);
	}

	public Object viewCart() {

		ToolCallback tool = getTool("viewCart");

		return tool.call("{}");
	}

	public Object removeProduct(String id) {

		ToolCallback tool = getTool("removeProduct");

		String input = """
				{
				  "id":"%s"
				}
				""".formatted(id);

		return tool.call(input);
	}

	public Object checkout() {

		ToolCallback tool = getTool("checkout");

		return tool.call("{}");
	}
}
