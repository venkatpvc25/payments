package com.pvc.payments.usecase;

import com.pvc.core.port.WalletPort;
import com.pvc.payments.recharge.RechargeOrder;
import com.pvc.payments.recharge.RechargeProcessor;

public class RechargeUseCase {

    private final WalletPort walletPort;
    private final RechargeProcessor processor;

    public RechargeUseCase(
            WalletPort walletPort,
            RechargeProcessor processor) {
        this.walletPort = walletPort;
        this.processor = processor;
    }

    public void recharge(RechargeOrder order) {

        walletPort.debit(
                order.getUserId(),
                order.getAmount(),
                order.getClientTxnId());

        processor.process(order);
    }
}
