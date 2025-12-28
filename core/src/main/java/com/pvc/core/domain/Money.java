package com.pvc.core.domain;

import java.math.BigDecimal;

public record Money(String currency, BigDecimal amount) {

    public Money {
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency cannot be null or blank");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be null or negative");
        }
    }
}