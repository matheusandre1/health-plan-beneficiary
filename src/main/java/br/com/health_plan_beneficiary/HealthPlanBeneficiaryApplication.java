package br.com.health_plan_beneficiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HealthPlanBeneficiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthPlanBeneficiaryApplication.class, args);
	}

}
