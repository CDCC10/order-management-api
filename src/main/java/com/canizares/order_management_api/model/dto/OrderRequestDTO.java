package com.canizares.order_management_api.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequestDTO {
    @NotBlank(message = "The customer's name is required")
    private String customerName;
    @DecimalMin(value = "0.5", message = "The amount min allowed is 0.5")
    private double totalAmount;
}
