package com.pvc.payments.operator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Operator {
    private final String code;
    private final String name;
    private final OperatorType type;

}
