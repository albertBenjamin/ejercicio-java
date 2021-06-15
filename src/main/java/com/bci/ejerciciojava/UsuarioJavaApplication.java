package com.bci.ejerciciojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.bci.ejerciciojava.models.dao")
@EnableJpaAuditing(auditorAwareRef = "securityConfig")
@EnableTransactionManagement
@SpringBootApplication
public class UsuarioJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioJavaApplication.class, args);
	}

}
