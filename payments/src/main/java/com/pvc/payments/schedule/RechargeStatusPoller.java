package com.pvc.payments.schedule;

import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pvc.payments.entity.Transaction;
import com.pvc.payments.provider.ProviderStatus;
import com.pvc.payments.provider.ProviderStatusPort;
import com.pvc.payments.repo.TransactionRepository;

@Component
@EnableScheduling
public class RechargeStatusPoller {

    private final TransactionRepository repo;
    private final ProviderStatusPort providerStatusPort;

    public RechargeStatusPoller(
            TransactionRepository repo,
            ProviderStatusPort providerStatusPort) {
        this.repo = repo;
        this.providerStatusPort = providerStatusPort;
    }

    @Scheduled(fixedDelay = 30000)
    public void pollPendingRecharges() {

        Optional<List<Transaction>> pending = repo.findByStatus("PENDING");

        if (pending.isPresent()) {
            for (Transaction txn : pending.get()) {
                ProviderStatus status = providerStatusPort.checkStatus(
                        txn.getProviderTxnId());

                if (status == ProviderStatus.SUCCESS) {
                    txn.setStatus("SUCCESS");
                } else if (status == ProviderStatus.FAILED) {
                    txn.setStatus("FAILED");
                }
            }
        }
    }
}
