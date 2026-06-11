package com.spring.ai.service;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.util.List;

@Component
public class McpToolInspector {

	@Autowired(required = false)
	private List<ToolCallback> toolCallbacks;

	@PostConstruct
	public void inspect() {

		System.out.println("\n===== TOOL CALLBACKS =====");

		if (toolCallbacks == null || toolCallbacks.isEmpty()) {
			System.out.println("NO TOOL CALLBACKS FOUND");
			return;
		}

		toolCallbacks.forEach(tool -> {
			System.out.println(tool.getClass().getName());
		});

		System.out.println("==========================");
	}
}
