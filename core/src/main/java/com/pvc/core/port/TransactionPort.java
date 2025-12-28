package com.pvc.core.port;

import com.pvc.core.domain.RechargeTransaction;

public interface TransactionPort {
    void save(RechargeTransaction txn);

    void updateStatus(String clientTxnId, String status);
}
