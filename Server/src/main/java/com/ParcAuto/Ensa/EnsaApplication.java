package com.ParcAuto.Ensa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.ParcAuto.Ensa.Affectation")
public class EnsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnsaApplication.class, args);
	}

}
