package com.pvc.core.port;

import com.pvc.core.domain.RechargeTransaction;

public interface ProviderPort {
    boolean recharge(RechargeTransaction txn);
}
