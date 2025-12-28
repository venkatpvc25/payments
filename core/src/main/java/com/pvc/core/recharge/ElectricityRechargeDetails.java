package com.pvc.core.recharge;

public final class ElectricityRechargeDetails
        implements RechargeDetails {

    private final String consumerNumber;
    private final String board;

    public ElectricityRechargeDetails(
            String consumerNumber,
            String board) {

        this.consumerNumber = consumerNumber;
        this.board = board;
    }

    @Override
    public void validate() {
        if (consumerNumber == null || consumerNumber.isBlank()) {
            throw new IllegalArgumentException("Consumer number required");
        }
        if (board == null || board.isBlank()) {
            throw new IllegalArgumentException("Electricity board required");
        }
    }
}
