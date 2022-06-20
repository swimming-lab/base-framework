package com.swm.baseframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BaseFrameworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseFrameworkApplication.class, args);
	}

}
