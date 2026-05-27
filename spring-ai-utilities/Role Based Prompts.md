Role-Based Prompting is one of the simplest and most effective prompt engineering techniques. By assigning a role through the system() prompt, you can control how the AI behaves and tailor responses for different domains such as teaching, interviewing, architecture design, code reviews, and HR assistance. This technique is widely used in real-world AI applications built with Spring AI.

Examples:

Java Technical Architect
Teacher
Interviewer
HR Manager
Code Reviewer
DevOps Engineer
Why Use Role-Based Prompts?

Without a role:

Explain Spring Boot

Response may be generic.

With a role:

You are a Senior Java Technical Architect.
Explain Spring Boot.

Response becomes more architecture-focused and professional.

Benefits:

Better quality responses
Domain-specific answers
Consistent tone and style
More realistic AI assistants
How It Works

Spring AI provides the system() method to define the AI's role.

chatClient.prompt()
        .system("You are a Java Technical Architect")
        .user("Explain Microservices")
        .call()
        .content();
Components
system() → Defines the AI role
user() → Contains the user question
call() → Sends request to the model
content() → Returns generated response



GET /role/java-architect?question=Explain Microservices Architecture
GET /role/interviewer?topic=Spring Boot
GET /role/hr?question=How should I answer why I want to change jobs?
GET /role/code-reviewer?code=public class Test{}


Dynamic Role-Based Prompt

Instead of creating multiple endpoints, a single endpoint can support multiple roles dynamically.

Dynamic Role-Based Prompt

GET /dynamic-role?role=Teacher&question=Explain Spring Boot
GET /dynamic-role?role=HR Manager&question=How should I answer salary expectations?
GET /dynamic-role?role=Java Technical Architect&question=Explain Microservices
GET /dynamic-role?role=Java Interviewer&question=Spring Security

Sample Flow
User Request
      |
      v
Controller
      |
      v
ChatClient
      |
      v
System Role Assigned
      |
      v
User Question Added
      |
      v
LLM Generates Response
      |
      v
Response Returned
