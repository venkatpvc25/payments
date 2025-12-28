package com.pvc.payments.adapter;

import org.springframework.stereotype.Component;

import com.pvc.payments.provider.ProviderResult;
import com.pvc.payments.provider.RechargeProvider;
import com.pvc.payments.recharge.RechargeOrder;

@Component
public class MockBbpsProvider implements RechargeProvider {

    @Override
    public ProviderResult recharge(RechargeOrder order) {

        // simulate success / failure
        if (order.getAmount().amount().doubleValue() < 1000) {
            return new ProviderResult(
                    true,
                    "BBPS-" + System.currentTimeMillis(),
                    "Recharge successful");
        }

        return new ProviderResult(
                false,
                null,
                "Amount exceeds limit");
    }
}
