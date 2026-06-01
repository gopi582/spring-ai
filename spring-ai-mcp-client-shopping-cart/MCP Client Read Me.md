#What You Already Have

#You already created:

Shopping Cart Spring Boot App
MongoDB Integration
Shopping Cart APIs

#Now we will:

Convert app into MCP Server
Connect Claude Desktop to MCP Server
Create MCP Client
Invoke tools from REST API

#Restart Claude Desktop

Close Claude Desktop completely.

Open again.

Claude now starts your MCP Server automatically.

#Verify MCP Tools

Inside Claude Desktop type:

#What tools are available?

You should see:

addToCart
viewCart
removeProduct
checkout

#http://localhost:8080/mcp/client/cart/add

Body:

{
  "productName":"iphone",
  "price":70000,
  "quantity":2
}

GET http://localhost:8080/mcp/client/tools
GET http://localhost:8080/mcp/client/cart
DELETE http://localhost:8080/mcp/client/cart/683c1a9a1ab22344f5e8f111
POST http://localhost:8080/mcp/client/checkout




#Architecture

REST API
   ↓
Spring Boot MCP Client
   ↓
ToolCallbackProvider
   ↓
STDIO Transport
   ↓
Spring AI MCP Server
   ↓
ShoppingCartTools
   ↓
MongoDB


#How MCP Communication Works

Client calls REST API
        ↓
Controller invokes Service
        ↓
Service finds MCP Tool
        ↓
ToolCallbackProvider invokes MCP Tool
        ↓
STDIO transport sends JSON-RPC request
        ↓
MCP Server executes tool
        ↓
Response returned to client

#End-to-End Flow
POST /cart/add
      ↓
Controller
      ↓
ShoppingCartMcpClientService
      ↓
ToolCallbackProvider
      ↓
MCP Tool Invocation
      ↓
ShoppingCart MCP Server
      ↓
MongoDB