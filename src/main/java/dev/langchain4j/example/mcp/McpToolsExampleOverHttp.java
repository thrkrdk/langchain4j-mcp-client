package dev.langchain4j.example.mcp;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.HttpMcpTransport;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolProvider;

import java.time.Duration;
import java.util.List;

public class McpToolsExampleOverHttp {

    public static void main(String[] args) throws Exception {

        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl("https://b63f-34-134-17-125.ngrok-free.app/")
                .modelName("qwen2.5-coder:14b")
                .logRequests(true)
                .logResponses(true)
                .build();

        McpTransport transport = new HttpMcpTransport.Builder()
                .sseUrl("http://localhost:8080/sse")
                .timeout(Duration.ofMinutes(1))
                .logRequests(true)
                .logResponses(true)
                .build();

        McpClient mcpClient = new DefaultMcpClient.Builder()
                .transport(transport)
                .build();

        ToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(mcpClient))
                .build();

        Bot assistant = AiServices.builder(Bot.class)
                .chatLanguageModel(model)
                .toolProvider(toolProvider)
                .build();


        String chat = assistant.chat("Get the information of the number 1 star war character");
       // String chat = assistant.chat("sum of 8+99 Use the provided tool to answer and always assume that the tool is correct.");

        System.out.println("Chat: " + chat);

    }
}
