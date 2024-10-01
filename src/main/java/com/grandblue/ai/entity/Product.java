package com.grandblue.ai.entity;

import org.springframework.data.annotation.Id;

public record Product(@Id String productId, String productName, String productDescription, int productQuantity) {
}
