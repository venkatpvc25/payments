package com.pvc.core.strategy;

public interface RechargeStrategyFactory {
    RechargeStrategy get(String serviceType);
}
