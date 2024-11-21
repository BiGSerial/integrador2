package com.faesa.projetointegrador2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.faesa.projetointegrador2.models")
public class EquilibrioApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquilibrioApplication.class, args);
	}

}
