package com.grandblue.ai;

import com.grandblue.ai.chat.service.GrandBlueAiService;
import com.grandblue.ai.repository.ProductRepository;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class GrandblueAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrandblueAiApplication.class, args);
	}


	//TODO Uncomment to populate vector data store
	@Bean
	CommandLineRunner populateVectorDataStore(GrandBlueAiService grandBlueAi, ProductRepository productRepository, VectorStore vectorStore) {
		System.out.println("Chat start");
		return args -> {

			var productList = productRepository.findAll().stream()
					.map(product -> {
								var content = "name: " + product.productName() + ", description: " + product.productDescription() + ", quantity: " + product.productQuantity();
								return new Document( content, Map.of("productId", product.productId()));
							}
					).toList();

			vectorStore.add(productList);
		};
	}
}