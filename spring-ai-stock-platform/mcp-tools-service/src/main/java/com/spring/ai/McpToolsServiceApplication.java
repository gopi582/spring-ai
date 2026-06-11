package com.spring.ai;

import java.lang.reflect.Method;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableCaching
public class McpToolsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpToolsServiceApplication.class, args);
	}
	

@Bean
ApplicationRunner runner(ApplicationContext ctx) {
    return args -> {

        String[] tools = ctx.getBeanNamesForAnnotation(Component.class);

        for (String bean : tools) {
            Object obj = ctx.getBean(bean);

            for (Method m : obj.getClass().getMethods()) {
                if (m.isAnnotationPresent(Tool.class)) {
                    System.out.println("FOUND TOOL: " + m.getName());
                }
            }
        }
    };
}

}

