# Langchain4j APP MCP HOST/Client For Spring boot MCP server

## SSE Transport

Run [McpToolsExampleOverHttp.java](src/main/java/dev/langchain4j/example/mcp/McpToolsExampleOverHttp.java)

- Before run this example, you need to change ollama base url.
- Sse server runs 8080 port by default. If you want to change it `.sseUrl("http://localhost:8080/sse")` in the class

## STDIO Transport

Run [McpToolsExampleOverStdio.java](src/main/java/dev/langchain4j/example/mcp/McpToolsExampleOverStdio.java)

- Before run this example, you need to change ollama base url.
- Stdio uses podman by default. If you want to use docker, you need to change `docker` in the class.
- If you wantt to use different container name, you need to change  "configcius-mcp" to your container name in the
  class.