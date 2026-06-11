package com.spring.ai.config;

import java.util.List;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpToolConfig {

	@Bean
	ToolCallbackProvider toolCallbackProvider(ApplicationContext context) {

		List<Object> toolBeans = context.getBeansOfType(Object.class).values().stream()
				.filter(bean -> bean.getClass().getSimpleName().endsWith("Tools")).toList();

		System.out.println("MCP Tool Beans Found: " + toolBeans.size());

		return MethodToolCallbackProvider.builder().toolObjects(toolBeans.toArray()).build();
	}
}