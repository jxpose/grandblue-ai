package com.grandblue.ai.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.AdvisedRequest;
import org.springframework.ai.chat.client.RequestResponseAdvisor;
import org.springframework.ai.chat.model.ChatResponse;

import java.util.Map;

public class GrandBlueLogger implements RequestResponseAdvisor {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public AdvisedRequest adviseRequest(AdvisedRequest request, Map<String, Object> context) {
    logger.info("Request: {}", request.userText());
    return request;
  }

  @Override
  public ChatResponse adviseResponse(ChatResponse response, Map<String, Object> context) {
    logger.info("Response: {}", response.getResults());
    return response;
  }
}
