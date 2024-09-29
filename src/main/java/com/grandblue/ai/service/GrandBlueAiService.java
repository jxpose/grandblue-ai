package com.grandblue.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GrandBlueAiService {
  private final ChatClient chatClient;

  public GrandBlueAiService(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public String chat(String query) {
    var result = chatClient.prompt()
        .user(query)
        .call();

    return result.content();
  }
}
