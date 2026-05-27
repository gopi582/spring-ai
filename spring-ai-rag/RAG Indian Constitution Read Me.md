#RAG Workflow
Step 1: Upload Documents

Upload one or more PDF files:

HR_Policy.pdf
Leave_Policy.pdf
Insurance_Policy.pdf
Travel_Policy.pdf

#Step 2: PDF Reading

Spring AI extracts text from PDF:

PagePdfDocumentReader

#Step 3: Text Chunking

Large content is split into smaller chunks:

TokenTextSplitter

Example:

Chunk 1
Employees receive 20 days annual leave.

Chunk 2
Employees are entitled to 10 sick leaves.

Chunk 3
Parents can be covered under insurance.

#Step 4: Generate Embeddings

Ollama embedding model:

nomic-embed-text

converts text into vectors.

Example:

Employees receive 20 annual leaves

↓

[0.12,0.55,0.77,0.31...]

#Step 5: Store in PGVector

Each chunk stores:

Text
Embedding
Metadata

Example:

{
  "source":"Leave_Policy.pdf"
}

#Step 6: Ask Questions

User asks:

How many annual leaves are provided?

#Step 7: Similarity Search

Question converted to vector.

PGVector retrieves nearest matching chunks.

Example:

Employees receive 20 days of annual leave per year.

#Step 8: Build Prompt

Retrieved chunks become context:

Context:

Employees receive 20 days of annual leave per year.

Question:
How many annual leaves are provided?

#Step 9: Generate Answer

Qwen model generates:

Employees are entitled to 20 days of annual leave per calendar year.


#Connect to PostgreSQL:

docker exec -it pgvector psql -U postgres -d vectordb

#Count vectors:

SELECT COUNT(*) FROM vector_store;

#View stored content:

SELECT LEFT(content,500)
FROM vector_store
LIMIT 10;

#Search raw content:

SELECT content
FROM vector_store
WHERE content ILIKE '%annual leave%';

#Check vector dimensions:

SELECT vector_dims(embedding)
FROM vector_store
LIMIT 1;
















                    +----------------+
                    |     User       |
                    +-------+--------+
                            |
                            |
                            ▼
                 +---------------------+
                 | Spring Boot REST API|
                 +----------+----------+
                            |
            +---------------+---------------+
            |                               |
            ▼                               ▼

    PDF Upload Flow                Question Answer Flow

+----------------------+      +----------------------+
| Upload PDF Endpoint  |      | Ask Question API     |
+----------+-----------+      +----------+-----------+
           |                             |
           ▼                             ▼

+----------------------+      +----------------------+
| PagePdfDocumentReader|      | User Question        |
+----------+-----------+      +----------+-----------+
           |                             |
           ▼                             ▼

+----------------------+      +----------------------+
| TokenTextSplitter    |      | Embedding Generation |
+----------+-----------+      | (nomic-embed-text)   |
           |                  +----------+-----------+
           ▼                             |
                                         ▼
+----------------------+      +----------------------+
| Embedding Model      |      | PGVector Similarity  |
| nomic-embed-text     |      | Search              |
+----------+-----------+      +----------+-----------+
           |                             |
           ▼                             ▼

+----------------------+      +----------------------+
| PostgreSQL PGVector  |      | Retrieved Chunks     |
| Vector Database      |      +----------+-----------+
+----------+-----------+                 |
                                         ▼

                           +--------------------------+
                           | Build Context Prompt     |
                           +------------+-------------+
                                        |
                                        ▼

                           +--------------------------+
                           | Ollama - Qwen2.5:3b      |
                           +------------+-------------+
                                        |
                                        ▼

                           +--------------------------+
                           | Final Answer             |
                           +--------------------------+