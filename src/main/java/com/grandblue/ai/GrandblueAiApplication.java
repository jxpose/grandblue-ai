package com.grandblue.ai;

import com.grandblue.ai.service.GrandBlueAiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class GrandblueAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrandblueAiApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(GrandBlueAiService grandBlueAi) {
		System.out.println("Chat start");
		return args -> {
//			System.out.println(grandBlueAi.chat());
		};
	}

	@Bean
	ChatClient chatClient(ChatClient.Builder builder, @Value("classpath:/system.md") Resource resource) {
		return builder
				.defaultSystem(resource)
				.build();
	}

}
