package com.pvc.event.consumer;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.pvc.core.domain.event.RechargeFailedEvent;
import com.pvc.core.domain.event.RechargeRefundedEvent;
import com.pvc.core.domain.event.RechargeSuccessEvent;

@Component
public class RechargeEventConsumer {

    @EventListener
    public void onSuccess(RechargeSuccessEvent event) {
        System.out.println("Send SMS for success: " + event.clientTxnId());
    }

    @EventListener
    public void onFailure(RechargeFailedEvent event) {
        System.out.println("Send failure SMS: " + event.clientTxnId());
    }

    @EventListener
    public void onRefund(RechargeRefundedEvent event) {
        System.out.println(
                "Refund processed for txn " + event.clientTxnId());
    }

}
