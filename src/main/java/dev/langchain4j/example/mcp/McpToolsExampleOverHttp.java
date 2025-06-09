package dev.langchain4j.example.mcp;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.HttpMcpTransport;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolProvider;

import java.time.Duration;
import java.util.List;

public class McpToolsExampleOverHttp {

    public static void main(String[] args) throws Exception {

        // 1- Change Ollama  url by using kaggle and ngrok
        // 2- Run Demo image in podman in SSE mode. Get the SSE URL from the podman image.
        // 3- Create ChatLanguageModel for Ollama
        // 4- Create Transport for MCP with SSE URL
        // 5- Create MCP Client
        // 6- MCP server needs ToolProvider to get the tools from the server.
        // 7- Create an aiservices instance with Bot type.
        // 8- Call the assistant with a tool.

        // create ChatLanguageModel for Ollama
        ChatModel model = OllamaChatModel.builder()
                .baseUrl("https://8b66-35-232-97-5.ngrok-free.app/")
                .modelName("qwen2.5-coder:14b")
                .logRequests(true)
                .logResponses(true)
                .build();

        // Create Transport for MCP with SSE URL
        McpTransport transport = new HttpMcpTransport.Builder()
                .sseUrl("http://localhost:8080/sse")  // get SSE URL from the podman image. Usually it is http://localhost:8080/sse
                .timeout(Duration.ofMinutes(1))  // Langchain4j or SSE server can be throwing timeout exception.
                .logRequests(true)
                .logResponses(true)
                .build();

        transport.checkHealth();


        // Create MCP Client
        McpClient mcpClient = new DefaultMcpClient.Builder()
                .transport(transport)
                .build();

        mcpClient.checkHealth(); // for timeout exception. check if the server is up and running. not mandatory.

        // Default prompts are loaded from the MCP server. If prompts are not provided from the server, List will be null.
        // mcpClient.listPrompts(); //  spring boot'ta  bu kısım çalışmıyor. büyük ihtimal register yapamadım :)

        // All resources are loaded from the MCP server. If resources are not provided from the server, List will be null.
        // mcpClient.listResources(); //  spring boot'ta  bu kısım çalışmıyor. büyük ihtimal register yapamadım :)


        // MCP server needs ToolProvider to get the tools from the server.
        ToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(mcpClient))
                .build();


        // Create an aiservices instance with Bot type.
        Bot assistant = AiServices.builder(Bot.class)
                .chatModel(model)
                .toolProvider(toolProvider) // tools list are added to the assistant.
                .build();


        // Call the assistant with a tool.
        String chat = assistant.chat("Who am I");
        // String chat = assistant.chat("Get the information of the number 1 star war character. Always use tool result in your response.");

        System.out.println("Chat: " + chat);

    }
}
