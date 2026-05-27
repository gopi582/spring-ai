Chat Memory is a foundational capability for conversational AI applications. It enables Spring AI applications to retain context across multiple interactions, resulting in more intelligent, personalized, and natural conversations. By combining Chat Memory with Ollama, developers can build stateful AI assistants, support bots, learning platforms, and enterprise knowledge assistants that understand and remember user interactions.

Example:

Without Memory
User: My name is Test
AI: Nice to meet you Test

User: What is my name?
AI: I don't know your name

The second request has no knowledge of the first request.

With Memory
User: My name is Test
AI: Nice to meet you Test

User: What is my name?
AI: Your name is Test

The AI remembers previous messages because they are stored in memory.

Architecture
User
  |
  v
REST Controller
  |
  v
ChatClient
  |
  v
MessageChatMemoryAdvisor
  |
  v
MessageWindowChatMemory
  |
  v
Ollama Model

Why Use a Memory Window?

Benefits:

Reduced token usage
Better performance
Lower memory consumption
Prevents excessively large prompts