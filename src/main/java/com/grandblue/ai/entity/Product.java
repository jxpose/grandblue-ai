package com.grandblue.ai.entity;

import org.springframework.data.annotation.Id;

public record Product(@Id String productId, String productName, String productDescription, int productQuantity, int unitPrice) {

  @Override
  public String toString() {
    return String.format("name: %s, description: %s, quantity: %s, unit price: %s", productName, productDescription, productQuantity, unitPrice);
  }
}
