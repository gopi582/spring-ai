A complete Spring AI project demonstrating how to convert Large Language Model (LLM) responses into Java objects using various Spring AI Output Converters.

This project uses Spring AI, Ollama, and Llama 3.2 to generate structured responses such as:

•	Java Beans 
•	Java Records 
•	Enums 
•	Nested Objects 
•	Lists 
•	Maps 
•	Generic Collections 
________________________________________
Features
Bean Conversion
Convert AI responses directly into Java POJOs.
Example:
{
  "name": "John",
  "age": 30,
  "department": "IT"
}
Uses:
BeanOutputConverter<Employee>
________________________________________
Record Conversion
Convert AI responses into Java Records.
Example:
{
  "name": "Scott",
  "age": 35,
  "department": "HR"
}
Uses:
BeanOutputConverter<EmployeeRecord>
________________________________________
Enum Conversion
Convert AI responses into Enum fields.
Example:
{
  "name": "David",
  "department": "IT"
}
Uses:
BeanOutputConverter<EmployeeWithEnum>
________________________________________
Nested Object Conversion
Convert AI responses containing child objects.
Example:
{
  "name": "John",
  "age": 30,
  "department": "IT",
  "address": {
    "city": "Hyderabad",
    "state": "Telangana",
    "country": "India"
  }
}
Uses:
BeanOutputConverter<Employee>
________________________________________
List Conversion
Generate collections from AI output.
Example:
[
  "Hyderabad",
  "Mumbai",
  "Chennai",
  "Delhi",
  "Bangalore"
]
Uses:
ListOutputConverter
________________________________________
Map Conversion
Convert AI responses into key-value pairs.
Example:
{
  "name": "John",
  "age": 30,
  "department": "IT"
}
Uses:
MapOutputConverter
________________________________________
Generic Collection Conversion
Convert AI responses into:
List<Employee>
Uses:
BeanOutputConverter<List<Employee>>
with
ParameterizedTypeReference<List<Employee>>
________________________________________
Technology Stack
•	Java 21 
•	Spring Boot 3.x 
•	Spring AI 1.1.x 
•	Ollama 
•	Llama 3.2 
•	Maven 
________________________________________
Prerequisites
Install Ollama
Download and install Ollama:
https://ollama.com/download
Verify installation:
ollama --version
________________________________________
Pull Llama Model
ollama pull llama3.2
Verify model:
ollama list
Example:
NAME       ID         SIZE
llama3.2   xxxxxxxx   2GB
________________________________________
Project Structure
src/main/java
│
├── controller
│   └── ConverterController.java
│
├── service
│   └── ConverterService.java
│
├── dto
│   ├── Employee.java
│   ├── Address.java
│   ├── Department.java
│   ├── EmployeeRecord.java
│   └── EmployeeWithEnum.java
│
└── SpringAiApplication.java
________________________________________
Configuration
application.yml
spring:
  ai:
    ollama:
      base-url: http://localhost:11434

      chat:
        model: llama3.2
________________________________________
Running Ollama
Start Ollama:
ollama serve
Default URL:
http://localhost:11434
________________________________________
Running Application
Build project:
mvn clean install
Run application:
mvn spring-boot:run
Application starts at:
http://localhost:8082
________________________________________
REST APIs
Bean Conversion
GET /converter/bean
Response:
{
  "name": "John",
  "age": 30,
  "department": "IT"
}
________________________________________
Record Conversion
GET /converter/record
Response:
{
  "name": "Scott",
  "age": 35,
  "department": "HR"
}
________________________________________
Enum Conversion
GET /converter/enum
Response:
{
  "name": "David",
  "department": "IT"
}
________________________________________
Nested Object Conversion
GET /converter/nested
Response:
{
  "name": "John",
  "age": 30,
  "department": "IT",
  "address": {
    "city": "Hyderabad",
    "state": "Telangana",
    "country": "India"
  }
}
________________________________________
Cities List
GET /converter/cities
Response:
[
  "Hyderabad",
  "Mumbai",
  "Delhi",
  "Chennai",
  "Bangalore"
]
________________________________________
Marks List
GET /converter/marks
Response:
[
  90,
  85,
  88,
  76,
  95
]
________________________________________
Map Conversion
GET /converter/map
Response:
{
  "name": "John",
  "age": 30,
  "department": "IT"
}
________________________________________
Employee Collection
GET /converter/employees
Response:
[
  {
    "name": "John",
    "age": 30,
    "department": "IT"
  },
  {
    "name": "David",
    "age": 28,
    "department": "HR"
  }
]

