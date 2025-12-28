package com.pvc.payments.operator;

import java.util.List;

public interface OperatorPort {
    List<Operator> getOperators(OperatorType type);
}
