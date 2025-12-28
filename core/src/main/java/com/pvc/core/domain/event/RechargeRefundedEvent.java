package com.pvc.core.domain.event;

import java.math.BigDecimal;
import java.time.Instant;

public record RechargeRefundedEvent(
        String clientTxnId,
        String userId,
        BigDecimal amount,
        Instant timestamp) implements DomainEvent {

    @Override
    public String eventType() {
        return "RECHARGE_REFUNDED";
    }
}
