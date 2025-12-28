package com.pvc.core.domain.state;

import com.pvc.core.domain.RechargeTransaction;

public class InitiatedState implements RechargeState {

    @Override
    public void next(RechargeTransaction txn) {
        txn.setState(new PendingState());
    }

    @Override
    public void fail(RechargeTransaction txn) {
        throw new IllegalStateException("INIT cannot fail directly");
    }
}
