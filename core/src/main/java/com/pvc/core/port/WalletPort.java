package com.pvc.core.port;

import com.pvc.core.domain.Money;

public interface WalletPort {
    void debit(String userId, Money amount, String clientTxnId);

    void credit(String userId, Money amount, String clientTxnId);

    void refund(String userId, Money amount, String clientTxnId);
}
