package org.horivera.codingchallenge.checkout;

import org.horivera.codingchallenge.checkout.conf.MapperConfig;
import org.horivera.codingchallenge.checkout.conf.RestConfig;
import org.horivera.codingchallenge.checkout.conf.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(value = { SwaggerConfig.class, MapperConfig.class, RestConfig.class })
@SpringBootApplication
public class CheckoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckoutApplication.class, args);
	}

}
