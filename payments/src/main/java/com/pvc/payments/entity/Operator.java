package com.pvc.payments.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "operator")
public class Operator {

    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String code;

    private String name;
    private String type;
    private boolean active;
}
