This project introduces embeddings using Spring AI and Ollama. It demonstrates how text can be converted into vectors, how semantic similarity can be calculated, and how embeddings serve as the foundation for advanced AI systems such as semantic search, recommendation engines, document intelligence platforms, and RAG applications. It is a key step toward building production-grade AI applications with Spring AI.

What are Embeddings?
================================
Embeddings transform text into a list of numbers (vector representation).

Example:

Input:
Java Programming

Converted into:

[0.123, -0.456, 0.789, ...]

The vector represents the meaning of the text rather than the exact words.


Test APIs
=====================

Generate Embedding
=======================
GET http://localhost:8080/embedding?text=Java Programming


Semantic Examples:
==================
Example API Calls

Search Spring Boot
===========================
GET http://localhost:8080/semantic-search?query=Spring Boot Developer

Example Response:

[
  {
    "document": "Java Developer with Spring Boot experience",
    "score": 0.95
  },
  {
    "document": "Spring Boot Microservices Architect",
    "score": 0.93
  },
  {
    "document": "Full Stack Developer using React and Spring Boot",
    "score": 0.90
  },
  {
    "document": "Java Technical Architect with Microservices",
    "score": 0.84
  },
  {
    "document": "Backend Engineer using Java and PostgreSQL",
    "score": 0.79
  }
]

Search AI Engineer
============================
GET http://localhost:8080/semantic-search?query=Generative AI Developer

Example Response:

[
  {
    "document": "AI Engineer using Ollama and Spring AI",
    "score": 0.97
  },
  {
    "document": "Python Data Scientist with Machine Learning skills",
    "score": 0.83
  }
]

Response:

[
  0.0213,
  -0.1345,
  0.8721,
  ...
]
-------------------------------------------------------------------------------------------------------------------
Multiple Embeddings
============================
GET http://localhost:8080/embeddings

Response:

[
  [0.12,0.23,...],
  [0.45,0.11,...],
  [0.67,0.89,...]
]
---------------------------------------------------------------------------------------
Similarity Check
===============================
GET http://localhost:8080/similarity?text1=Java Developer&text2=Spring Boot Developer

Response:

0.91

Interpretation:

Score	Meaning
0.90 - 1.00	Very Similar
0.70 - 0.89	Similar
0.40 - 0.69	Moderately Related
0.00 - 0.39	Different
------------------------------------------------------------------------------------------------
Embedding Dimension
=================================
GET http://localhost:8080/embedding-dimension?text=Java

Response:

Embedding Dimension: 768

(dimension depends on the embedding model used)

Example Learning Scenarios
GET /embedding?text=Spring AI
GET /embedding?text=Microservices Architecture
GET /similarity?text1=Java&text2=Spring Boot
GET /similarity?text1=Java&text2=Pizza Recipe


Architecture
==========================
Client
   |
   v
REST Controller
   |
   v
Spring AI EmbeddingModel
   |
   v
Ollama Embedding Model
(nomic-embed-text)
   |
   v
Vector Output

Flow:
==================
User sends text through REST API.
Spring AI forwards the text to Ollama.
Ollama generates an embedding vector.
Spring AI returns the vector to the application.
Application can perform similarity calculations or further processing.