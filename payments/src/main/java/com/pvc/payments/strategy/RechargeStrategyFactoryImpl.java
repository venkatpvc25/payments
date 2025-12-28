package com.pvc.payments.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;
import com.pvc.core.strategy.RechargeStrategy;
import com.pvc.core.strategy.RechargeStrategyFactory;

@Component
public class RechargeStrategyFactoryImpl implements RechargeStrategyFactory {

    private final Map<String, RechargeStrategy> strategies;

    public RechargeStrategyFactoryImpl(Map<String, RechargeStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public RechargeStrategy get(String serviceType) {
        RechargeStrategy strategy = strategies.get(serviceType);
        if (strategy == null) {
            throw new IllegalArgumentException(
                    "Unsupported recharge type: " + serviceType);
        }
        return strategy;
    }
}
