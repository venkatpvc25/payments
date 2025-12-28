package com.pvc.event.producer;

import org.springframework.stereotype.Component;

import com.pvc.core.port.EventPort;

@Component
public class RechargeEventProducer implements EventPort {

    @Override
    public void publish(Object event) {
        System.out.println("Publishing recharge event: " + event);
    }

}
