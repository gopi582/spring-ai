package com.spring.ai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;

import com.spring.ai.tools.ShoppingCartTools;

@Configuration
public class McpToolConfig {

    @Bean
    ToolCallbackProvider shoppingCartToolProvider(ShoppingCartTools tools) {

        return MethodToolCallbackProvider.builder()
                .toolObjects(tools)
                .build();
    }
}