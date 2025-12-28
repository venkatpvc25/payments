package com.pvc.core.recharge;

public final class MobileRechargeDetails
        implements RechargeDetails {

    private final String mobileNumber;
    private final String operator;
    private final String circle;

    public MobileRechargeDetails(
            String mobileNumber,
            String operator,
            String circle) {

        this.mobileNumber = mobileNumber;
        this.operator = operator;
        this.circle = circle;
    }

    @Override
    public void validate() {
        if (mobileNumber == null || !mobileNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid mobile number");
        }
        if (operator == null || operator.isBlank()) {
            throw new IllegalArgumentException("Operator required");
        }
        if (circle == null || circle.isBlank()) {
            throw new IllegalArgumentException("Circle required");
        }
    }

    // getters
}
