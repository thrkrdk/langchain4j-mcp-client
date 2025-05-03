# Langchain4j APP MCP HOST/Client For Spring boot MCP server

- [Main branch](https://github.com/thrkrdk/langchain4j-mcp-client) : Initial start.
- [01-connect-to-ai branch](https://github.com/thrkrdk/langchain4j-mcp-client/tree/01-connect-to-ai): Private LLM connection
  - Create Bot Interface
  - Connect to Ollama Qwen2.5-coder:14b
  - Ask For the information of the number 1 star war character
- [02-use-mcp-server-sse](https://github.com/thrkrdk/langchain4j-mcp-client/tree/02-use-mcp-server-sse): Connecting SSE Mcp Server
  - Change Ollama  url by using kaggle and ngrok
  - Run Demo image in podman in SSE mode. Get the SSE URL from the podman image.
  - Create ChatLanguageModel for Ollama
  - Create Transport for MCP with SSE URL
  - Create MCP Client
  - MCP server needs ToolProvider to get the tools from the server.
  - Create an aiservices instance with Bot type.
  - Call the assistant with a tool.
- [03-use-mcp-server-stadio](https://github.com/thrkrdk/langchain4j-mcp-client/tree/03-use-mcp-server-stadio): Connecting Stdio Mcp Server
  - Change Ollama  url by using kaggle and ngrok
  - Run Demo image in podman in STDIO mode.
  - Create ChatLanguageModel for Ollama
  - Create Transport for MCP with STDIO
  - Create MCP Client
  - MCP server needs ToolProvider to get the tools from the server.
  - Create an aiservices instance with Bot type.
  - Call the assistant with a tool.
 
# 03-use-mcp-server-stadio is the FINAL BRANCH. all code is in this branch
