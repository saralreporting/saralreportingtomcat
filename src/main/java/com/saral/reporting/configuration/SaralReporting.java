package com.saral.reporting.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.saral*"})
@EntityScan("com.saral*")
@EnableJpaRepositories("com.saral*")
public class SaralReporting {

	public static void main(String[] args) {
		SpringApplication.run(SaralReporting.class, args);
	}

}

