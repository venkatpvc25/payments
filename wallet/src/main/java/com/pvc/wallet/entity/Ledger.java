package com.pvc.wallet.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "wallet_ledger", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "client_txn_id", "type" })
})
public class Ledger {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String type; // CREDIT / DEBIT

    @Column(nullable = false)
    private String reference;

    @Column(name = "client_txn_id", nullable = false)
    private String clientTxnId;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();
}
