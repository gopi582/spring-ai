#Architecture

User
  ↓
AI Agent (Spring AI)
  ↓
MCP Client
  ↓
MCP Server
  ↓
Shopping Cart Service
  ↓
MongoDB

#Project Flow

#MCP Server

The MCP Server exposes shopping cart tools.

#Example tools:

addToCart
viewCart
removeProduct
checkout

#MCP Client

The MCP Client connects to the MCP Server using stdio transport.

The client automatically loads all tools exposed by the MCP Server.

#AI Agent

The AI Agent uses:

Ollama LLM
MCP Tools

The agent understands natural language prompts and automatically invokes tools.

Example:

Add Samsung S25 price 80000 quantity 1 to cart and show all cart items

The agent internally executes:

1. addToCart()
2. viewCart()


#Add Products
Add iPhone 16 price 95000 quantity 1 to cart
Add Samsung S25 Ultra price 125000 quantity 2 to cart
Add OnePlus 13 price 70000 quantity 1
Add AirPods Pro price 25000 quantity 3 to cart

#View Cart
Show all cart items
Display my shopping cart
What products are available in my cart?
View complete cart details

#Checkout
Calculate total cart amount
What is my final cart price?
Show total shopping cart bill

#Remove Product
Remove the cart item whose productId is 6a1d5d07e162e8b7816d36e4
Delete product whose productId is 6a1d5d07e162e8b7816d36e4
Remove item with productId 6a1d5d07e162e8b7816d36e4 from cart

#Multi-Step Prompts
Add + View
Add Vivo X200 price 65000 quantity 1 to cart and show all cart items
Add + Checkout
Add MacBook Pro price 220000 quantity 1 and calculate total cart amount


#Add Multiple Products
Add Samsung S25 price 80000 quantity 1,
add AirPods Pro price 25000 quantity 2,
and show all cart items

#Full Workflow
Add iPhone 16 price 95000 quantity 1,
add Apple Watch price 45000 quantity 1,
show all cart items,
and calculate total amount