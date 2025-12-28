package com.pvc.payments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.payments.dto.ProviderWebhookRequest;
import com.pvc.payments.entity.Transaction;
import com.pvc.payments.repo.TransactionRepository;

@RestController
@RequestMapping("/api/webhooks/provider")
public class ProviderWebhookController {

    private final TransactionRepository repo;

    public ProviderWebhookController(TransactionRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<?> handleWebhook(
            @RequestBody ProviderWebhookRequest req) {

        Transaction txn = repo.findByProviderTxnId(req.providerTxnId())
                .orElseThrow();

        txn.setProviderStatus(req.status());

        if ("SUCCESS".equals(req.status())) {
            txn.setStatus("SUCCESS");
        } else if ("FAILED".equals(req.status())) {
            txn.setStatus("FAILED");
        }

        return ResponseEntity.ok().build();
    }
}
