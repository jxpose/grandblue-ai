package com.grandblue.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GrandBlueAiService {
  private final ChatClient chatClient;

  public GrandBlueAiService(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public String chat() {
    var result = chatClient.prompt()
        .user("Do you have any googles?")
        .call();

    return result.content();
  }
}
