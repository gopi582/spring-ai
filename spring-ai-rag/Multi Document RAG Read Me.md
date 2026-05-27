# Spring AI RAG Application with Ollama and PGVector

A Retrieval Augmented Generation (RAG) application built using:

- Spring Boot 4
- Spring AI 2.0.0-M6
- Ollama
- Qwen 2.5 (LLM)
- Nomic Embed Text (Embedding Model)
- PostgreSQL + PGVector
- Docker

The application allows users to upload PDF documents, generate embeddings, store them in a vector database, and ask questions based on the uploaded content.

---

# Features

✅ PDF Upload

✅ Automatic Text Extraction

✅ Text Chunking

✅ Embedding Generation

✅ Vector Storage in PGVector

✅ Semantic Similarity Search

✅ Retrieval Augmented Generation (RAG)

✅ Local LLM using Ollama

✅ Spring AI Integration

✅ REST APIs

---

# Architecture

```text
                           +------------------+
                           |   User / Client  |
                           +--------+---------+
                                    |
                                    |
                                    v
                       +-------------------------+
                       | Spring Boot REST APIs   |
                       +------------+------------+
                                    |
                 -----------------------------------
                 |                                 |
                 |                                 |
                 v                                 v

      PDF Upload Flow                    Question Answer Flow

+-----------------------+        +--------------------------+
| Upload PDF Endpoint   |        | Ask Question Endpoint    |
+-----------+-----------+        +------------+-------------+
            |                                 |
            v                                 v
+-----------------------+        +--------------------------+
| PagePdfDocumentReader |        | User Question            |
+-----------+-----------+        +------------+-------------+
            |                                 |
            v                                 v
+-----------------------+        +--------------------------+
| TokenTextSplitter     |        | Embedding Model          |
+-----------+-----------+        | nomic-embed-text         |
            |                    +------------+-------------+
            v                                 |
+-----------------------+                     v
| Embedding Model       |        +--------------------------+
| nomic-embed-text      |        | Similarity Search        |
+-----------+-----------+        | PGVector                |
            |                    +------------+-------------+
            v                                 |
+-----------------------+                     v
| PGVector Database     |        +--------------------------+
| Store Embeddings      |        | Retrieved Chunks         |
+-----------------------+        +------------+-------------+
                                              |
                                              v
                              +-----------------------------+
                              | QuestionAnswerAdvisor       |
                              +-------------+---------------+
                                            |
                                            v
                              +-----------------------------+
                              | Qwen2.5:3b LLM             |
                              +-------------+---------------+
                                            |
                                            v
                              +-----------------------------+
                              | Final AI Response           |
                              +-----------------------------+
```

---

# RAG Flow

## Step 1: Upload PDF

User uploads a PDF document.

Example:

```
constitution_of_india.pdf
```

---

## Step 2: Read PDF

Spring AI extracts text using:

```java
PagePdfDocumentReader
```

---

## Step 3: Chunking

Large text is divided into smaller chunks.

Example:

```
Chunk 1
Article 1-10

Chunk 2
Article 11-20

Chunk 3
Article 21-30
```

---

## Step 4: Embedding Generation

Each chunk is converted into vectors using:

```
nomic-embed-text
```

Example:

```text
"The President of India"

↓

[0.234,0.552,0.129,0.892,...]
```

---

## Step 5: Store in PGVector

Store:

- Chunk Text
- Embedding Vector
- Metadata

inside PostgreSQL PGVector.

---

## Step 6: Ask Question

Example:

```
What is Article 21?
```

---

## Step 7: Question Embedding

Question converted to vector.

```
What is Article 21?

↓

[0.345,0.673,0.123...]
```

---

## Step 8: Similarity Search

PGVector searches nearest vectors.

Returns relevant chunks.

Example:

```
Article 21:
Protection of life and personal liberty...
```

---

## Step 9: Prompt Construction

Spring AI:

```java
QuestionAnswerAdvisor
```

injects retrieved content into prompt.

Example:

```
Context:
Article 21 protects life and personal liberty.

Question:
What is Article 21?
```

---

## Step 10: Response Generation

Qwen model generates answer.

Example:

```
Article 21 guarantees protection of life and personal liberty and states that no person shall be deprived of these rights except according to procedure established by law.
```

# API Endpoints

## Upload PDF

### Request

```http
POST /pdf/upload
```

Form Data:

```text
file = constitution_of_india.pdf
```

### Response

```text
PDF Uploaded Successfully
```

---

## Search Chunks

```http
GET /rag/search?query=Article 21
```

Returns retrieved chunks from vector database.

---

## Ask Question

```http
GET /rag/ask?question=What is Article 21?
```

Example Response:

```json
{
  "answer":"Article 21 guarantees protection of life and personal liberty."
}
```

---


# Learning Concepts Covered

- Large Language Models (LLMs)
- Embeddings
- Vector Databases
- Semantic Search
- RAG Architecture
- Spring AI ChatClient
- QuestionAnswerAdvisor
- PGVector Integration
- Ollama Integration
- PDF Processing
- Retrieval Pipelines
- AI Application Development

---

# Future Enhancements

- Chat Memory
- Multi PDF Support
- Metadata Filtering
- Streaming Responses (SSE)
- Hybrid Search
- MCP Integration
- AI Agents
- Observability
- Cost Tracking
- Multi-Model RAG
- Document Versioning

---

