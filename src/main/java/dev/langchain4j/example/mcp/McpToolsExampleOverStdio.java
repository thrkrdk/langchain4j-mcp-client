package dev.langchain4j.example.mcp;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolProvider;

import java.util.List;

public class McpToolsExampleOverStdio {
    public static void main(String[] args) throws Exception {

        // 1- Change Ollama  url by using kaggle and ngrok
        // 2- Run Demo image in podman in STDIO mode.
        // 3- Create ChatLanguageModel for Ollama
        // 4- Create Transport for MCP with STDIO

        // 5- Create MCP Client
        // 6- MCP server needs ToolProvider to get the tools from the server.
        // 7- Create an aiservices instance with Bot type.
        // 8- Call the assistant with a tool.

        // 9- Importan: put debug dev.langchain4j.mcp.client.transport.stdio.ProcessIOHandler.run for error

        // create ChatLanguageModel for Ollama
        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl("write here ollama url")
                .modelName("qwen2.5-coder:14b")
                .build();

        // Create Transport for MCP with STDIO. Add create podman image command.
        McpTransport transport = new StdioMcpTransport.Builder()
                .command(List.of(
                        "podman",
                        "run",
                        "-i",
                        "--rm",
                        "swapi-mcp-starter-stdio"
                ))
                .build();

        // Create MCP Client
        McpClient mcpClient = new DefaultMcpClient.Builder()
                .transport(transport)
                .build();

        mcpClient.checkHealth(); // for timeout exception. check if the server is up and running. not mandatory.

        // Default prompts are loaded from the MCP server. If prompts are not provided from the server, List will be null.
        // mcpClient.listPrompts();  // spring boot'ta kısım çalışmıyor. büyük ihtimal register yapamadım :)
        // All resources are loaded from the MCP server. If resources are not provided from the server, List will be null.
        // mcpClient.listResources();  //  spring boot'ta  bu kısım çalışmıyor. büyük ihtimal register yapamadım :)


        // MCP server needs ToolProvider to get the tools from the server.
        ToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(mcpClient))
                .build();

        // Create an aiservices instance with Bot type.
        Bot bot = AiServices.builder(Bot.class)
                .chatLanguageModel(model)
                .toolProvider(toolProvider) // add tool provider to the bot
                .build();

        // Call the assistant with a tool.
        String chat = bot.chat("Do you know anything about me?");
        // String chat = bot.chat("Get the information of the number 1 star war character. Always use tool result in your response.");

        System.out.println("Chat: " + chat);
    }
}
