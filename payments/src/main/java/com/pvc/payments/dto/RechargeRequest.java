package com.pvc.payments.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RechargeRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String clientTxnId;

    @NotBlank
    private String serviceType;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    private String currency;
}
