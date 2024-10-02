package com.grandblue.ai.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Product {

  @Id
  UUID productId;
  String productName;
  String productDescription;
  int productQuantity;
  int unitPrice;
  @Override
  public String toString() {
    return String.format("id: %s, name: %s, description: %s, quantity: %s, unit price: %s", productId.toString(), productName, productDescription, productQuantity, unitPrice);
  }

  public void purchase(int quantity) {
//    if (quantity > productQuantity) throw new IllegalArgumentException("Product quantity is lesser than the purchase quantity");
    productQuantity -= quantity;
  }

  public void restock(int quantity) {
    productQuantity += quantity;
  }
}
