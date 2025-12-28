package com.pvc.payments.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvc.payments.entity.Operator;

public interface OperatorRepository
        extends JpaRepository<Operator, UUID> {

    List<Operator> findByTypeAndActiveTrue(String type);
}
