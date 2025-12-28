package com.pvc.payments.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.payments.plan.PlanPort;
import com.pvc.payments.plan.RechargePlan;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanPort planPort;

    public PlanController(PlanPort planPort) {
        this.planPort = planPort;
    }

    @GetMapping("/{operatorCode}")
    public List<RechargePlan> getPlans(
            @PathVariable String operatorCode) {
        return planPort.getPlans(operatorCode);
    }
}
