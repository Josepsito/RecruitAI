package com.proyecto.RecruitAI.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

    @Value("${ollama.api.url}")
    private String ollamaApiUrl;

    @Bean
    public OllamaApi ollamaApi(){
        return OllamaApi.builder()
                .baseUrl(ollamaApiUrl)
                .build();
    }

    @Bean
    public OllamaChatModel ollamaChatModel(
            OllamaApi ollamaApi,
            OllamaChatOptions options
    ){
        return OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .options(options)
                .build();
    }

    @Bean
    public OllamaChatOptions ollamaChatOptions(){
        return OllamaChatOptions.builder()
                .temperature(0.5)
                .model("llama3:latest")
                .build();
    }

    @Bean
    public ChatModel chatModel(OllamaChatModel ollamaChatModel){
        return ollamaChatModel;
    }

    @Bean
    public ChatClient chatClient(ChatModel chatModel){
        return ChatClient
                .builder(chatModel)
                .build();
    }
}