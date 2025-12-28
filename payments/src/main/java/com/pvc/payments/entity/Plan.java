package com.pvc.payments.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "recharge_plan")
public class Plan {

    @Id
    private UUID id;

    @Column(name = "operator_code")
    private String operatorCode;

    @Column(precision = 19, scale = 2)
    private BigDecimal amount;

    private String description;
    private Integer validityDays;
    private boolean active;
}
