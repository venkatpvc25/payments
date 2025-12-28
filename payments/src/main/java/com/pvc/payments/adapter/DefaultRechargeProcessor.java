package com.pvc.payments.adapter;

import org.springframework.stereotype.Component;

import com.pvc.payments.provider.ProviderResult;
import com.pvc.payments.provider.RechargeProvider;
import com.pvc.payments.recharge.RechargeOrder;
import com.pvc.payments.recharge.RechargeProcessor;

@Component
public class DefaultRechargeProcessor implements RechargeProcessor {

    private final RechargeProvider provider;

    public DefaultRechargeProcessor(RechargeProvider provider) {
        this.provider = provider;
    }

    @Override
    public void process(RechargeOrder order) {

        ProviderResult result = provider.recharge(order);

        if (!result.success()) {
            throw new RuntimeException(result.message());
        }
    }
}
