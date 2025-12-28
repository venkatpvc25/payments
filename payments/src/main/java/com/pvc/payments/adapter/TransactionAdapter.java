package com.pvc.payments.adapter;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.pvc.core.domain.RechargeTransaction;
import com.pvc.core.port.TransactionPort;
import com.pvc.payments.entity.Transaction;
import com.pvc.payments.repo.TransactionRepository;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class TransactionAdapter implements TransactionPort {

    private final TransactionRepository repo;

    public TransactionAdapter(TransactionRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(RechargeTransaction txn) {
        Transaction e = Transaction.builder()
                .id(txn.getTxnId())
                .clientTxnId(txn.getClientTxnId())
                .userId(txn.getUserId())
                .serviceType(txn.getServiceType())
                .amount(txn.getAmount().amount())
                .currency(txn.getAmount().currency())
                .status(txn.getStatus().name())
                .createdAt(txn.getCreatedAt())
                .build();

        repo.save(e);
    }

    @Override
    public void updateStatus(String clientTxnId, String status) {
        Transaction e = repo.findByClientTxnId(clientTxnId)
                .orElseThrow();

        e.setStatus(status);
        e.setUpdatedAt(Instant.now());
    }
}
