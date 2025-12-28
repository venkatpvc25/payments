package com.pvc.core.domain.event;

import java.math.BigDecimal;
import java.time.Instant;

public record RechargeInitiatedEvent(
        String clientTxnId,
        String userId,
        String serviceType,
        BigDecimal amount,
        String currency,
        Instant timestamp) implements DomainEvent {

    @Override
    public String eventType() {
        return "RECHARGE_INITIATED";
    }
}
