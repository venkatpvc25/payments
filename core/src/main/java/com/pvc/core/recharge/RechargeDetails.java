package com.pvc.core.domain;

public sealed interface RechargeDetails
        permits MobileRechargeDetails,
        DthRechargeDetails,
        ElectricityRechargeDetails {

    void validate();
}
