package com.pvc.payments.dto;

import java.math.BigDecimal;

import com.pvc.payments.recharge.RechargeType;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class RechargeApiRequest {

    private String userId;
    private String clientTxnId;
    private RechargeType type;
    private String operator;
    private String mobileNumber; // for mobile
    private String consumerNumber; // for electricity
    private BigDecimal amount;
    private String currency;
}
