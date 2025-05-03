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

    // We will let the AI read the contents of this file
    public static final String FILE_TO_BE_READ = "src/main/resources/file.txt";

    /**
     * This example uses the `server-filesystem` MCP server to showcase how
     * to allow an LLM to interact with the local filesystem.
     * <p>
     * Running this example requires npm to be installed on your machine,
     * because it spawns the `server-filesystem` as a subprocess via npm:
     * `npm exec @modelcontextprotocol/server-filesystem@0.6.2`.
     * <p>
     * Of course, feel free to swap out the server with any other MCP server.
     * <p>
     * The communication with the server is done directly via stdin/stdout.
     * <p>
     * IMPORTANT: when executing this, make sure that the working directory is
     * equal to the root directory of the project
     * (`langchain4j-examples/mcp-example`), otherwise the program won't be able to find
     * the proper file to read. If you're working from another directory,
     * adjust the path inside the StdioMcpTransport.Builder() usage in the main method.
     */
    public static void main(String[] args) throws Exception {

        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl("https://b63f-34-134-17-125.ngrok-free.app/")
                .modelName("qwen2.5-coder:14b")
//                .logRequests(true)
//                .logResponses(true)
                .build();

        McpTransport transport = new StdioMcpTransport.Builder()
                .command(List.of(
                        "podman",
                        "run",
                        "-i",
                        "--rm",
                        "configcius-mcp"
                ))
                .logEvents(true)
                .build();

        McpClient mcpClient = new DefaultMcpClient.Builder()
                .transport(transport)
                .build();

        ToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(mcpClient))
                .build();

        Bot bot = AiServices.builder(Bot.class)
                .chatLanguageModel(model)
                .toolProvider(toolProvider)
                .build();

        String chat = bot.chat("Get the information of the number 1 star war character");
        System.out.println("Chat: " + chat);
        // String chat = bot.chat("sum of 8+99 Use the provided tool to answer and always assume that the tool is correct.");
    }
}
