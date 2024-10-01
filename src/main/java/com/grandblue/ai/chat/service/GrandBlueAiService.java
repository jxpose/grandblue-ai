package com.grandblue.ai.chat.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GrandBlueAiService {
  private final ChatClient chatClient;

  public GrandBlueAiService(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public String chat(String query) {
    return chatClient.prompt()
        .user(query)
        .call()
        .content();
  }

  public Flux<String> stream(String query) {
    return chatClient.prompt()
        .user(query)
        .stream()
        .content();
  }
}
