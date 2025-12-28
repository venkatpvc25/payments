package com.pvc.core.domain.state;

import com.pvc.core.domain.RechargeTransaction;

public class FailedState implements RechargeState {

    @Override
    public void next(RechargeTransaction txn) {
        throw new IllegalStateException("FAILED cannot proceed");
    }

    @Override
    public void fail(RechargeTransaction txn) {
        throw new IllegalStateException("Already FAILED");
    }
}
