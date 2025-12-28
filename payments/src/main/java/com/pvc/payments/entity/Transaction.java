package com.pvc.payments.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recharge_transaction")
public class Transaction {

    @Id
    private UUID id;

    @Column(name = "client_txn_id", nullable = false, unique = true)
    private String clientTxnId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String serviceType;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String status;

    @Column(name = "provider_txn_id", nullable = false, unique = true)
    private String providerTxnId;

    @Column(name = "provider_status", nullable = false)
    private String providerStatus;

    private Instant createdAt;
    private Instant updatedAt;
}
