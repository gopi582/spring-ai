package com.spring.ai;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AiAgentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiAgentServiceApplication.class, args);
	}
	
	@Bean
	ApplicationRunner verifyTools(ToolCallbackProvider provider) {
	    return args -> {

	        System.out.println("\n===== MCP TOOLS =====");

	        var tools = provider.getToolCallbacks();

	        System.out.println("Tool Count = " + tools.length);

	        for (var tool : tools) {
	            System.out.println(
	                    "Tool -> " +
	                    tool.getToolDefinition().name());
	        }

	        System.out.println("=====================\n");
	    };
	}
	
}
