package com.pvc.payments.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.core.domain.Money;
import com.pvc.core.usecase.RechargeUseCase;
import com.pvc.payments.dto.RechargeApiRequest;
import com.pvc.payments.recharge.ElectricityRechargeDetails;
import com.pvc.payments.recharge.MobileRechargeDetails;
import com.pvc.payments.recharge.RechargeDetails;
import com.pvc.payments.recharge.RechargeOrder;

@RestController
@RequestMapping("/api/recharge")
public class RechargeApiController {

    private final RechargeUseCase useCase;

    public RechargeApiController(RechargeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<?> recharge(
            @RequestBody RechargeApiRequest req) {

        RechargeDetails details = switch (req.getType()) {
            case MOBILE_PREPAID ->
                new MobileRechargeDetails(
                        req.getMobileNumber(),
                        req.getOperator(),
                        "KA");

            case ELECTRICITY ->
                new ElectricityRechargeDetails(
                        req.getConsumerNumber(),
                        req.getOperator());

            default ->
                throw new IllegalArgumentException("Unsupported type");
        };

        RechargeOrder order = new RechargeOrder(
                req.getClientTxnId(),
                req.getUserId(),
                req.getType(),
                details,
                new Money(req.getCurrency(), req.getAmount()));

        useCase.recharge(order);

        return ResponseEntity.ok(
                Map.of(
                        "status", "PROCESSING",
                        "clientTxnId", req.getClientTxnId()));
    }
}
