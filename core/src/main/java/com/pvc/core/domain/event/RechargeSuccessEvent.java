package com.pvc.core.domain.event;

import java.time.Instant;

public record RechargeSuccessEvent(
        String clientTxnId,
        String userId,
        String serviceType,
        Instant timestamp) implements DomainEvent {

    @Override
    public String eventType() {
        return "RECHARGE_SUCCESS";
    }
}
