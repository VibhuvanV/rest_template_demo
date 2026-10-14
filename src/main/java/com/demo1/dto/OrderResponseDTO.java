package com.demo1.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponseDTO {
    Long orderId;
    Long productId;
    String productName;
    Integer quantity;
    BigDecimal orderAmount;
    String status;
}
