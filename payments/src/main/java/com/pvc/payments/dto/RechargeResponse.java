package com.pvc.payments.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.pvc.core.domain.RechargeTransaction;

import lombok.Data;

@Data
public class RechargeResponse {

    private UUID txnId;
    private String clientTxnId;
    private String status;
    private BigDecimal amount;
    private String currency;
    private String serviceType;
    private Instant timestamp;

    public static RechargeResponse mapToResponse(RechargeTransaction txn) {
        RechargeResponse res = new RechargeResponse();
        res.setTxnId(txn.getTxnId());
        res.setClientTxnId(txn.getClientTxnId());
        res.setStatus(txn.getStatus().name());
        res.setAmount(txn.getAmount().amount());
        res.setCurrency(txn.getAmount().currency());
        res.setServiceType(txn.getServiceType());
        res.setTimestamp(Instant.now());
        return res;
    }
}
