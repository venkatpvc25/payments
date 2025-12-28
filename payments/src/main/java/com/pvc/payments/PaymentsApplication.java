package com.pvc.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.pvc.core",
		"com.pvc.wallet",
		"com.pvc.payments",
		"com.pvc.event",
		"com.pvc.notification",
		"com.pvc"
})
@EnableJpaRepositories(basePackages = { "com.pvc.wallet.repo", "com.pvc.payments.repo" })
@EntityScan(basePackages = { "com.pvc.wallet.entity", "com.pvc.payments.entity" })
public class PaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApplication.class, args);
	}

}
