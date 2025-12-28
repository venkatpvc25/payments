package com.pvc.payments.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pvc.core.port.EventPort;
import com.pvc.core.port.NotificationPort;
import com.pvc.core.port.TransactionPort;
import com.pvc.core.port.WalletPort;
import com.pvc.core.strategy.RechargeStrategyFactory;
import com.pvc.core.usecase.ProcessRechargeUseCase;

@Configuration
public class UseCaseConfig {

    @Bean
    public ProcessRechargeUseCase processRechargeUseCase(
            WalletPort walletPort,
            RechargeStrategyFactory factory,
            EventPort eventPort,
            NotificationPort notificationPort, TransactionPort transactionPort) {

        return new ProcessRechargeUseCase(
                walletPort,
                factory,
                eventPort,
                notificationPort, transactionPort);
    }
}
