package com.pvc.payments.adapter;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pvc.payments.plan.PlanPort;
import com.pvc.payments.plan.RechargePlan;

@Component
public class InMemoryPlanAdapter implements PlanPort {

    @Override
    public List<RechargePlan> getPlans(String operatorCode) {
        return List.of(
                new RechargePlan("P199", operatorCode,
                        new BigDecimal("199"),
                        "Unlimited calls + 1.5GB/day",
                        28));
    }
}
