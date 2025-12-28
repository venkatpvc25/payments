package com.pvc.payments.dto;

public record ProviderWebhookRequest(
        String providerTxnId,
        String status,
        String message) {
}
