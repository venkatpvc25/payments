package com.pvc.payments.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.payments.operator.Operator;
import com.pvc.payments.operator.OperatorPort;
import com.pvc.payments.operator.OperatorType;

@RestController
@RequestMapping("/api/operators")
public class OperatorController {

    private final OperatorPort operatorPort;

    public OperatorController(OperatorPort operatorPort) {
        this.operatorPort = operatorPort;
    }

    @GetMapping
    public List<Operator> getOperators(
            @RequestParam OperatorType type) {
        return operatorPort.getOperators(type);
    }
}
