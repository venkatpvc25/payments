package com.pvc.core.domain.state;

import com.pvc.core.domain.RechargeTransaction;

public interface RechargeState {
    void next(RechargeTransaction txn);

    void fail(RechargeTransaction txn);
}
