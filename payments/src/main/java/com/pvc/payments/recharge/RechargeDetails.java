package com.pvc.payments.recharge;

public sealed interface RechargeDetails
        permits MobileRechargeDetails,
        DthRechargeDetails,
        ElectricityRechargeDetails {

    void validate();
}
