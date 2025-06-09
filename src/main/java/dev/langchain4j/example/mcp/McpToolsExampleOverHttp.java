package dev.langchain4j.example.mcp;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;

public class McpToolsExampleOverHttp {

    public static void main(String[] args) throws Exception {

        // 1- Create Bot Interface
        // 2- Connect to Ollama Qwen2.5-coder:14b
        // 3- Ask For the information of the number 1 star war character

        // Create ChatLanguageModel For ollama
        ChatModel model = OllamaChatModel.builder()
                .baseUrl("https://94c7-34-127-113-117.ngrok-free.app") // todo: add here ollama server url by using kaggle and ngrok
                .modelName("qwen2.5-coder:14b")
                .build();


        // Create AI service with Bot type.
        Bot assistant = AiServices.builder(Bot.class)
                .chatModel(model)
                .build();


        // Ask for the information of the number 1 star war character to the assistant
        // String chat = assistant.chat("Get the information of the number 1 star war character");
        String chat = assistant.chat("Do you know me?");

        System.out.println("Chat: " + chat);

    }
}
