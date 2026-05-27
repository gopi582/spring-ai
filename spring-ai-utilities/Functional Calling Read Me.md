This project shows how Spring AI can automatically call Java methods to fetch live data from external systems. Instead of generating guessed answers, the AI uses real-time information and provides accurate responses to users.

Project Flow

User Question
      |
      v
Spring AI
      |
      v
Weather Tool
      |
      v
Weather Service
      |
      v
Live Weather API
      |
      v
Weather Response
      |
      v
AI Generated Answer

Components
WeatherResponse

Stores weather information.

public record WeatherResponse(
        String city,
        Double temperature,
        String condition) {
}
WeatherService

Calls the live weather API and extracts weather details.

Responsibilities:

Call external API
Parse JSON response
Return WeatherResponse object

Example:

WeatherResponse response =
        weatherService.getWeather("Hyderabad");
WeatherTools

Exposes the weather function to Spring AI.

@Tool
public WeatherResponse getWeather(String city)

Purpose:

Allows AI to invoke Java methods automatically
Returns live weather information
FunctionCallingService

Communicates with Ollama using Spring AI.

chatClient.prompt()
          .user(question)
          .tools(weatherTools)
          .call()
          .content();

Responsibilities:

Receive user question
Register available tools
Execute tool if needed
Return final AI response
FunctionCallingController

REST endpoint for user requests.

@GetMapping("/function")
public String ask(
        @RequestParam String question)

Example:

GET /function?question=What is the weather in Hyderabad?

Example: http://localhost:8082/function?question=What%20is%20the%20weather%20in%20Tenali 

The current weather in Tenali is sunny with a temperature of 41.0°.


