package com.pvc.payments.controller;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.core.domain.Money;
import com.pvc.core.domain.RechargeTransaction;
import com.pvc.core.usecase.ProcessRechargeUseCase;
import com.pvc.payments.dto.RechargeRequest;
import com.pvc.payments.dto.RechargeResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recharges")
public class RechargeController {

    private final ProcessRechargeUseCase useCase;

    public RechargeController(ProcessRechargeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public RechargeResponse recharge(
            @Valid @RequestBody RechargeRequest request) {
        RechargeTransaction txn = useCase.execute(
                request.getUserId(),
                request.getClientTxnId(),
                new Money(request.getCurrency(), request.getAmount()),
                request.getServiceType());
        return RechargeResponse.mapToResponse(txn);
    }

}
