package com.pvc.core.recharge;

public final class DthRechargeDetails
        implements RechargeDetails {

    private final String subscriberId;
    private final String operator;

    public DthRechargeDetails(String subscriberId, String operator) {
        this.subscriberId = subscriberId;
        this.operator = operator;
    }

    @Override
    public void validate() {
        if (subscriberId == null || subscriberId.isBlank()) {
            throw new IllegalArgumentException("Subscriber ID required");
        }
        if (operator == null || operator.isBlank()) {
            throw new IllegalArgumentException("DTH operator required");
        }
    }
}
