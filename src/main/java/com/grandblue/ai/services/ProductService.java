package com.grandblue.ai.services;

import com.grandblue.ai.entity.Product;
import com.grandblue.ai.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final VectorStore vectorStore;

  public Product restockProduct(String productName, int quantity) {
    var dbProduct = productRepository.findAll().stream()
        .filter(product -> product.getProductName().equalsIgnoreCase(productName))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    dbProduct.restock(quantity);

    productRepository.save(dbProduct);

    var document = new Document(dbProduct.toString(), Map.of("productId", dbProduct.getProductId().toString()));

    vectorStore.delete(List.of(dbProduct.getProductId().toString()));
    vectorStore.add(List.of(document));

    return dbProduct;
  }
}
