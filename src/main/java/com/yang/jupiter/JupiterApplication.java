package com.yang.jupiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableCaching
@SpringBootApplication
//@ComponentScan(basePackages = {"com.yang.jupiter.core.controller.CustomErrorController","com.yang.jupiter.controller"})
public class JupiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JupiterApplication.class, args);
	}

}
