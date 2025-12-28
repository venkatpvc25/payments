package com.pvc.core.domain.state;

import com.pvc.core.domain.RechargeTransaction;

public class PendingState implements RechargeState {

    @Override
    public void next(RechargeTransaction txn) {
        txn.setState(new SuccessState());
    }

    @Override
    public void fail(RechargeTransaction txn) {
        txn.setState(new FailedState());
    }
}
