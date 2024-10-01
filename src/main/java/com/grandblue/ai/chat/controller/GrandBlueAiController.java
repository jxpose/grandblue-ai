package com.grandblue.ai.chat.controller;

import com.grandblue.ai.chat.service.GrandBlueAiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class GrandBlueAiController {

  private final GrandBlueAiService grandBlueAiService;

  public GrandBlueAiController(GrandBlueAiService grandBlueAiService) {
    this.grandBlueAiService = grandBlueAiService;
  }

  @GetMapping("/ai/chat")
  public String chat(@RequestParam(defaultValue = "Hi") String query) {
    return grandBlueAiService.chat(query);
  }

  @GetMapping("/ai/stream/chat")
  public Flux<String> streamChat(@RequestParam(defaultValue = "Hi") String query) {
    return grandBlueAiService.stream(query);
  }
}
