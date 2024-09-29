package com.grandblue.ai;

import com.grandblue.ai.service.GrandBlueAiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Map;

@SpringBootApplication
public class GrandblueAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrandblueAiApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(GrandBlueAiService grandBlueAi, ProductRepository productRepository, VectorStore vectorStore) {
		System.out.println("Chat start");
		return args -> {

			var productList = productRepository.findAll().stream()
					.map(product -> {
						var content = "name: " + product.productName() + ", description: " + product.productDescription();
						return new Document( content, Map.of("productId", product.productId()));
					}
			).toList();

			vectorStore.add(productList);
			var query = "Do you have diving mask?";

			System.out.println(grandBlueAi.chat(query));
		};
	}

	@Bean
	ChatClient chatClient(
			ChatClient.Builder builder,
			@Value("classpath:/system.md") Resource resource,
			VectorStore vectorStore
	) {
		return builder
				.defaultSystem(resource)
				.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()))
				.build();
	}

}

interface ProductRepository extends ListCrudRepository<Product, String> {}
record Product(@Id String productId, String productName, String productDescription){}