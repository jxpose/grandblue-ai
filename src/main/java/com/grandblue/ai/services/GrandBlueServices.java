package com.grandblue.ai.services;

import com.grandblue.ai.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;

@Component
@Slf4j
public class GrandBlueServices {


  public record PurchaseRequest(String productName, int purchaseQuantity) {}
  public record RestockRequest(String employeeName, String productName, int purchaseQuantity) {}
  public record RestockResponse(Product product, LocalDateTime currentDate, RestockResult result) {
    public enum RestockResult { SUCCESSFUL, FAILED }
  }


  @Bean
  @Description("Purchase product")
  public Function<PurchaseRequest, String> purchaseProduct(PurchaseService purchaseService) {
    return request -> {
      try {
        purchaseService.purchaseProduct(request.productName, request.purchaseQuantity);
        return "Purchase successful.";
      } catch (Exception e) {
        log.error("Purchase failed: {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage());
        return "Purchase Failed.";
      }
    };
  }

  @Bean
  @Description("Restock product")
  public Function<RestockRequest, RestockResponse> restockProduct(ProductService productService) {
    return request -> {
      try {
        var product = productService.restockProduct(request.productName, request.purchaseQuantity);

        log.info("Employee {} successfully restocked product: {}", request.employeeName, product.getProductName());
        return new RestockResponse(product, LocalDateTime.now(), RestockResponse.RestockResult.SUCCESSFUL);
      } catch (NestedRuntimeException e) {
        log.error(e.getMessage(), e);
        return new RestockResponse(null, LocalDateTime.now(), RestockResponse.RestockResult.FAILED);
      }
    };
  }
}
