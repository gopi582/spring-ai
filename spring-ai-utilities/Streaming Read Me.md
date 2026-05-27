This project demonstrates Spring AI streaming and non-streaming APIs using Ollama models.

It supports:

🔹 Streaming responses (token-by-token)
🔹 Multi-model support
🔹 System prompts
🔹 Code generation
🔹 JSON generation
🔹 Translation
🔹 Interview question generation
🔹 Q&A

1.Straming Chat (Multi Model)
================================================

Endpoint
GET /ai/stream?model={model}&message={message}

Example
GET http://localhost:8080/ai/stream?model=qwen2.5:3b&message=Explain Spring Boot
GET http://localhost:8080/ai/stream?model=qwen2.5:3b&message=What is Java?

2.System Prompt Streaming
==================================
Endpoint
GET /ai/system?message={message}

Example
GET http://localhost:8080/ai/system?message=Explain microservices

👉 Behavior:

Acts like a Senior Java Architect
Gives short and precise answers

3.Normal Chat (Non-Streaming)
========================================
Endpoint
GET /ai/chat?model={model}&message={message}

Example
GET http://localhost:8080/ai/chat?model=qwen2.5:3b&message=Explain Spring AI

4.Code Generation
===================
Endpoint
GET /ai/code?model={model}&prompt={prompt}

Example
GET http://localhost:8080/ai/code?model=qwen2.5:3b&prompt=Singleton pattern in Java

5.Summary Generation
===========================
Endpoint
GET /ai/summary?model={model}&text={text}

Example
GET http://localhost:8080/ai/summary?model=qwen2.5:3b&text=Spring Boot is a framework that simplifies Java development...

6.JSON Generation (Streaming)
=====================================
Endpoint
GET /ai/json?topic={topic}

Example
GET http://localhost:8080/ai/json?topic=Employee object with name, age, department
Output Example
{
  "name": "John",
  "age": 30,
  "department": "IT"
}

7.Translation (Streaming)
==================================
Endpoint
GET /ai/translate?text={text}

Example
GET http://localhost:8080/ai/translate?text=Good Morning, how are you?

👉 Output: Telugu translation

8 Interview Questions Generation
==================================
Endpoint
GET /ai/interview?topic={topic}

Example
GET http://localhost:8080/ai/interview?topic=Java Streams

👉 Output:

10 interview questions
==========================
9. ❓ Q&A Streaming
Endpoint
GET /ai/qa?question={question}

Example
GET http://localhost:8080/ai/qa?question=What is dependency injection?