package com.grandblue.ai.config;

import com.grandblue.ai.logging.GrandBlueLogger;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class GrandBlueAiConfig {

  @Bean
  ChatClient chatClient(
      ChatClient.Builder builder,
      @Value("classpath:/system.md") Resource resource,
      VectorStore vectorStore,
      ChatMemory chatMemory
  ) {
    return builder
        .defaultSystem(resource)
        .defaultAdvisors(
            new PromptChatMemoryAdvisor(chatMemory),
            new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()),
            new GrandBlueLogger()
        )
        .build();
  }

  @Bean
  ChatMemory chatMemory() {
    return new InMemoryChatMemory();
  }
}
