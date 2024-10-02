package com.grandblue.ai.services;

import com.grandblue.ai.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final ProductRepository productRepository;
  private final VectorStore vectorStore;

  public void purchaseProduct(String productName, int quantity) {
    var dbProduct = productRepository.findAll().stream()
        .filter(product -> product.getProductName().equalsIgnoreCase(productName))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    dbProduct.purchase(quantity);

    var product = productRepository.save(dbProduct);
    var document = new Document(product.toString(), Map.of("productId", product.getProductId().toString()));

    vectorStore.delete(List.of(product.getProductId().toString()));
    vectorStore.add(List.of(document));
  }
}
