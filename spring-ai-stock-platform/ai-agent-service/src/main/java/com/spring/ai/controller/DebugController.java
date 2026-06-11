package com.spring.ai.controller;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class DebugController {

    private final ToolCallbackProvider toolProvider;

    public DebugController(ToolCallbackProvider toolProvider) {
        this.toolProvider = toolProvider;
    }

    @GetMapping("/tools")
    public String tools() {

        return "Tool Provider Loaded: " +
                toolProvider.getToolCallbacks().length;
    }
}