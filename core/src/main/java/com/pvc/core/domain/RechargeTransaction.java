package com.pvc.core.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.pvc.core.domain.state.InitiatedState;
import com.pvc.core.domain.state.RechargeState;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder.Default;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class RechargeTransaction {

    private final UUID txnId;
    private final String clientTxnId;
    @Default
    private RechargeStatus status = RechargeStatus.INTIATED;
    private final String serviceType;
    private final Instant createdAt;
    private RechargeState state;
    private Money amount;
    private String userId;

    public RechargeTransaction(String clientTxnId, Money amount, String serviceType, String userId) {
        this.txnId = UUID.randomUUID();
        this.clientTxnId = clientTxnId;
        this.status = RechargeStatus.INTIATED;
        this.serviceType = serviceType;
        this.createdAt = Instant.now();
        this.state = new InitiatedState();
        this.amount = amount;
        this.userId = userId;
    }

    public void proceed() {
        state.next(this);
    }

    public void fail() {
        state.fail(this);
    }

}
