package com.pvc.core.usecase;

import java.time.Instant;

import com.pvc.core.domain.Money;
import com.pvc.core.domain.RechargeTransaction;
import com.pvc.core.domain.event.RechargeFailedEvent;
import com.pvc.core.domain.event.RechargeInitiatedEvent;
import com.pvc.core.domain.event.RechargeRefundedEvent;
import com.pvc.core.domain.event.RechargeSuccessEvent;
import com.pvc.core.port.EventPort;
import com.pvc.core.port.NotificationPort;
import com.pvc.core.port.TransactionPort;
import com.pvc.core.port.WalletPort;
import com.pvc.core.strategy.RechargeStrategy;
import com.pvc.core.strategy.RechargeStrategyFactory;

public class ProcessRechargeUseCase {

    private final WalletPort walletPort;
    private final RechargeStrategyFactory strategyFactory;
    private final EventPort eventPort;
    private final NotificationPort notificationPort;
    private final TransactionPort transactionPort;

    public ProcessRechargeUseCase(
            WalletPort walletPort,
            RechargeStrategyFactory strategyFactory,
            EventPort eventPort,
            NotificationPort notificationPort,
            TransactionPort transactionPort) {

        this.walletPort = walletPort;
        this.strategyFactory = strategyFactory;
        this.eventPort = eventPort;
        this.notificationPort = notificationPort;
        this.transactionPort = transactionPort;
    }

    public RechargeTransaction execute(
            String userId,
            String clientTxnId,
            Money amount,
            String serviceType) {

        RechargeStrategy strategy = strategyFactory.get(serviceType);

        RechargeTransaction txn = new RechargeTransaction(clientTxnId, amount, serviceType, userId);

        transactionPort.save(txn);

        eventPort.publish(new RechargeInitiatedEvent(
                clientTxnId,
                userId,
                serviceType,
                amount.amount(),
                amount.currency(),
                Instant.now()));

        walletPort.debit(userId, amount, clientTxnId);
        txn.proceed();
        transactionPort.updateStatus(clientTxnId, "PENDING");

        try {
            strategy.execute(txn);

            txn.proceed();
            transactionPort.updateStatus(clientTxnId, "SUCCESS");

            eventPort.publish(new RechargeSuccessEvent(
                    clientTxnId,
                    userId,
                    serviceType,
                    Instant.now()));

        } catch (Exception e) {
            txn.fail(); // PENDING → FAILED
            transactionPort.updateStatus(clientTxnId, "FAILED");

            walletPort.refund(userId, amount, clientTxnId);
            txn.proceed(); // FAILED → REFUNDED
            transactionPort.updateStatus(clientTxnId, "REFUNDED");

            eventPort.publish(new RechargeRefundedEvent(
                    clientTxnId,
                    userId,
                    amount.amount(),
                    Instant.now()));
            throw e;
        }

        return txn;
    }
}
