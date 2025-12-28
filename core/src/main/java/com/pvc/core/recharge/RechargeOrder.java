package com.pvc.core.recharge;

import java.time.Instant;
import java.util.UUID;

import com.pvc.core.domain.Money;

import lombok.Data;

@Data
public class RechargeOrder {

    private final UUID orderId;
    private final String clientTxnId;
    private final String userId;
    private final RechargeType type;
    private final RechargeDetails details;
    private final Money amount;
    private final Instant createdAt;

    public RechargeOrder(
            String clientTxnId,
            String userId,
            RechargeType type,
            RechargeDetails details,
            Money amount) {

        this.orderId = UUID.randomUUID();
        this.clientTxnId = clientTxnId;
        this.userId = userId;
        this.type = type;
        this.details = details;
        this.amount = amount;
        this.createdAt = Instant.now();

        details.validate(); // 🔒 domain-level validation
    }

    // getters
}
