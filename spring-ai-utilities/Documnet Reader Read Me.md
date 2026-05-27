This project demonstrates a complete document-processing workflow using Spring AI and Ollama. It reads documents from the resources folder, sends the content to an AI model, and returns meaningful insights such as summaries, keywords, entities, questions, and data analysis. It is a strong foundation for building advanced AI applications such as knowledge assistants, document analyzers, and RAG-based systems.

It uses Spring AI with Ollama to read and analyze different document formats stored in the src/main/resources/documents folder.

Supported document types:

PDF
DOCX
TXT
CSV
JSON

The application reads the document content and sends it to an Ollama model for analysis, summarization, keyword extraction, question generation, and entity extraction.


Example Requests
PDF Summary
GET http://localhost:8080/ollama/pdf-summary
PDF Keywords
GET http://localhost:8080/ollama/pdf-keywords
PDF Questions
GET http://localhost:8080/ollama/pdf-questions
PDF Entities
GET http://localhost:8080/ollama/pdf-entities
DOCX Summary
GET http://localhost:8080/ollama/docx-summary
CSV Analysis
GET http://localhost:8080/ollama/csv-analysis
CSV Insights
GET http://localhost:8080/ollama/csv-insights
JSON Analysis
GET http://localhost:8080/ollama/json-analysis