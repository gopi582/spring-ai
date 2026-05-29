#Spring AI MCP Mongo Shopping Cart

#A complete end-to-end MCP (Model Context Protocol) server implementation using:

Java 21
Spring Boot 3.5
Spring AI MCP Server
MongoDB
Claude Desktop MCP Integration
STDIO Transport

This project exposes shopping cart operations as MCP tools that can be invoked directly by Claude Desktop.

#MongoDB Setup

Start MongoDB locally:

mongod

Default connection used:mongodb://localhost:27017/shoppingdb

#Claude Desktop MCP Configuration

Open Claude Desktop configuration:

Windows
%APPDATA%\Claude\claude_desktop_config.json

Add:

{
  "mcpServers": {
    "shopping-cart-server": {
      "command": "java",
      "args": [
        "-jar",
        "C:/Test/Learning/spring-ai-mcp-mongo-shopping-cart/build/libs/spring-ai-mcp-mongo-shopping-cart-0.0.1-SNAPSHOT.jar"
      ]
    }
  }
}

Restart Claude Desktop after configuration changes.

#Verify MCP Connection

Successful logs:

Server started and connected successfully

#Tool discovery:

"tools":[
  {
    "name":"addToCart"
  },
  {
    "name":"viewCart"
  },
  {
    "name":"removeProduct"
  },
  {
    "name":"checkout"
  }
]


#Future Enhancements
Authentication
Inventory management
Order history
Payment integration
Vector search
AI product recommendations
RAG integration
Multi-user carts
Kafka integration
Kubernetes deployment

