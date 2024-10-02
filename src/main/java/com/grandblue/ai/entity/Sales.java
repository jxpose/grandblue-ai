package com.grandblue.ai.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Sales {

  @Id
  UUID orderId;

  List<Product> productList;

  int salesAmount;

  LocalDateTime purchaseDate;
}
