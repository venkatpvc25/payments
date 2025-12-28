package com.pvc.core.domain.event;

import java.time.Instant;

public record RechargeFailedEvent(
        String clientTxnId,
        String userId,
        String reason,
        Instant timestamp) implements DomainEvent {

    @Override
    public String eventType() {
        return "RECHARGE_FAILED";
    }
}
