package com.pvc.core.strategy;

import com.pvc.core.domain.RechargeTransaction;

public interface RechargeStrategy {
    void execute(RechargeTransaction txn);
}
