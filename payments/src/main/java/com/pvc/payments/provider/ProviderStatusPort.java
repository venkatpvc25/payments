package com.pvc.payments.provider;

public interface ProviderStatusPort {
    ProviderStatus checkStatus(String providerTxnId);
}
