package com.pvc.wallet.adapter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.pvc.core.domain.Money;
import com.pvc.core.exception.InsufficientBalanceException;
import com.pvc.core.port.WalletPort;
import com.pvc.wallet.entity.Ledger;
import com.pvc.wallet.repo.LedgerRepository;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class WalletAdapter implements WalletPort {

    private final LedgerRepository repo;

    public WalletAdapter(LedgerRepository repo) {
        this.repo = repo;
    }

    @Override
    public void debit(String userId, Money amount, String clientTxnId) {
        if (repo.existsByClientTxnIdAndType(clientTxnId, "DEBIT")) {
            return;
        }

        BigDecimal balance = repo.calculateBalance(userId);

        if (balance.compareTo(amount.amount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        save(userId, amount.amount(), "DEBIT", clientTxnId);
    }

    @Override
    public void credit(String userId, Money amount, String clientTxnId) {
        BigDecimal amt = amount.amount();
        save(userId, amt, "CREDIT", clientTxnId);
    }

    @Override
    public void refund(String userId, Money amount, String clientTxnId) {
        if (repo.existsByClientTxnIdAndType(clientTxnId, "REFUND")) {
            return;
        }
        save(userId, amount.amount(), "REFUND", clientTxnId);
    }

    private void save(String userId, BigDecimal amt, String type, String clientTxnId) {
        Ledger e = Ledger.builder()
                .userId(userId)
                .amount(amt)
                .type(type)
                .reference("RECHARGE")
                .clientTxnId(clientTxnId)
                .build();
        repo.save(e);
    }
}
