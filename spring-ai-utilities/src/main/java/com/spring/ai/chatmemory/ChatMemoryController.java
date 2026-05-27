package com.spring.ai.chatmemory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatMemoryController {

    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    public ChatMemoryController(ChatClient.Builder builder) {

        this.chatMemory = MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build();

        this.chatClient = builder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory)
                                .build())
                .build();
    }

    @GetMapping("/chat")
    public String chat(
            @RequestParam("conversationId") String conversationId,
            @RequestParam("message") String message) {

        return chatClient.prompt()
                .user(message)
                .advisors(a -> a.param(
                        ChatMemory.CONVERSATION_ID,
                        conversationId))
                .call()
                .content();
    }

    @GetMapping("/chat/clear")
    public String clearMemory(
            @RequestParam("conversationId") String conversationId) {

        chatMemory.clear(conversationId);

        return "Memory cleared for conversation: "
                + conversationId;
    }
}
