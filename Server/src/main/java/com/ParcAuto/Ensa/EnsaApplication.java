package com.ParcAuto.Ensa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.ParcAuto.Ensa.Affectation")
public class EnsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnsaApplication.class, args);
	}

}
