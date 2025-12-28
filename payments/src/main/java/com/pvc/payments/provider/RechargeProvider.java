package com.pvc.payments.provider;

import com.pvc.payments.recharge.RechargeOrder;

public interface RechargeProvider {
    ProviderResult recharge(RechargeOrder order);
}
