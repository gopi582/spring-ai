package com.spring.ai.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ToolInspector {

	private final ApplicationContext context;

	public ToolInspector(ApplicationContext context) {
		this.context = context;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void printTools() {

		System.out.println("===== TOOL BEANS =====");

		String[] beans = context.getBeanDefinitionNames();

		for (String bean : beans) {

			try {

				Object obj = context.getBean(bean);

				if (obj.getClass().getSimpleName().endsWith("Tools")) {

					System.out.println(bean + " -> " + obj.getClass().getName());
				}

			} catch (Exception e) {

				System.out.println("Skipping bean: " + bean);
			}
		}
	}
}