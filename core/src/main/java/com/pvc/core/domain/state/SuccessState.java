package com.pvc.core.domain.state;

import com.pvc.core.domain.RechargeTransaction;

public class SuccessState implements RechargeState {

    @Override
    public void next(RechargeTransaction txn) {
        throw new IllegalStateException("Already SUCCESS");
    }

    @Override
    public void fail(RechargeTransaction txn) {
        throw new IllegalStateException("SUCCESS cannot fail");
    }
}
