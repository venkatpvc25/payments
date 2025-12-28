package com.pvc.payments.adapter;

import org.springframework.stereotype.Component;

import com.pvc.payments.provider.ProviderStatus;
import com.pvc.payments.provider.ProviderStatusPort;

@Component
public class MockBbpsStatusAdapter
        implements ProviderStatusPort {

    @Override
    public ProviderStatus checkStatus(String providerTxnId) {

        if (providerTxnId.endsWith("5")) {
            return ProviderStatus.PENDING;
        }
        return ProviderStatus.SUCCESS;
    }
}
