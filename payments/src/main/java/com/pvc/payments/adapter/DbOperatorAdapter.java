package com.pvc.payments.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pvc.payments.operator.Operator;
import com.pvc.payments.operator.OperatorPort;
import com.pvc.payments.operator.OperatorType;
import com.pvc.payments.repo.OperatorRepository;

@Component
public class DbOperatorAdapter implements OperatorPort {

    private final OperatorRepository repo;

    public DbOperatorAdapter(OperatorRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Operator> getOperators(OperatorType type) {
        return repo.findByTypeAndActiveTrue(type.name())
                .stream()
                .map(e -> new Operator(
                        e.getCode(),
                        e.getName(),
                        OperatorType.valueOf(e.getType())))
                .toList();
    }
}
