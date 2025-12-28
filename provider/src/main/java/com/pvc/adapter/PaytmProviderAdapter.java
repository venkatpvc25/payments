package com.pvc.adapter;

import org.springframework.stereotype.Component;

import com.pvc.client.PaytmClient;
import com.pvc.core.domain.RechargeTransaction;
import com.pvc.core.port.ProviderPort;

@Component
public class PaytmProviderAdapter implements ProviderPort {

    private final PaytmClient client;

    public PaytmProviderAdapter(PaytmClient client) {
        this.client = client;
    }

    @Override
    public boolean recharge(RechargeTransaction txn) {
        return client.recharge("9999999999", 222.0);
    }
}
