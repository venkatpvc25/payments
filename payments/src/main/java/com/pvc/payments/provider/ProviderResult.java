package com.pvc.payments.provider;

public record ProviderResult(
                boolean success,
                String providerTxnId,
                String message) {
}
