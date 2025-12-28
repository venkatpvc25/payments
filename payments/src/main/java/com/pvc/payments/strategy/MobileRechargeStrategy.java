package com.pvc.payments.strategy;

import org.springframework.stereotype.Component;

import com.pvc.core.domain.RechargeTransaction;
import com.pvc.core.exception.MobileRechargeException;
import com.pvc.core.port.ProviderPort;
import com.pvc.core.strategy.RechargeStrategy;

@Component("MOBILE")
public class MobileRechargeStrategy implements RechargeStrategy {

    private final ProviderPort providerPort;

    public MobileRechargeStrategy(ProviderPort providerPort) {
        this.providerPort = providerPort;
    }

    @Override
    public void execute(RechargeTransaction txn) {
        boolean success = providerPort.recharge(txn);
        if (!success) {
            throw new MobileRechargeException("Mobile recharge failed");
        }
    }
}
