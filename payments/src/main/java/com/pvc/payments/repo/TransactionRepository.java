package com.pvc.payments.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pvc.payments.entity.Transaction;

@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction, UUID> {

    Optional<Transaction> findByClientTxnId(String clientTxnId);

    Optional<List<Transaction>> findByStatus(String status);

    Optional<Transaction> findByProviderTxnId(String providerTxnId);
}
