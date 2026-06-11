package com.spring.ai.config;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class BeanInspector {

	private final ApplicationContext context;

	public BeanInspector(ApplicationContext context) {
		this.context = context;
	}

	@PostConstruct
	public void inspect() {

		System.out.println("\n===== MCP BEANS =====");

		for (String bean : context.getBeanDefinitionNames()) {

			if (bean.toLowerCase().contains("mcp")) {

				System.out.println(bean);
			}
		}

		System.out.println("=====================\n");
	}
}
