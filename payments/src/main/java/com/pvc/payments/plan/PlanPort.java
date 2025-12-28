package com.pvc.payments.plan;

import java.util.List;

public interface PlanPort {
    List<RechargePlan> getPlans(String operatorCode);
}
