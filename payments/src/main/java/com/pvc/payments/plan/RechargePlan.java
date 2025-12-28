package com.pvc.payments.plan;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RechargePlan {
    private final String planId;
    private final String operatorCode;
    private final BigDecimal amount;
    private final String description;
    private final int validityDays;
}
