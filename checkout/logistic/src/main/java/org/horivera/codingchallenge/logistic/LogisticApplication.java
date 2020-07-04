package org.horivera.codingchallenge.logistic;

import org.horivera.codingchallenge.logistic.conf.MapperConfig;
import org.horivera.codingchallenge.logistic.conf.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(value = { SwaggerConfig.class, MapperConfig.class })
@SpringBootApplication
public class LogisticApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticApplication.class, args);
	}

}
