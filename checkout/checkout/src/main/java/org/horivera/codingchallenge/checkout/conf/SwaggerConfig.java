package org.horivera.codingchallenge.checkout.conf;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metaData()).select()
				.apis(RequestHandlerSelectors.basePackage("org.horivera.codingchallenge.checkout.controller")).build();
	}

	private ApiInfo metaData() {
		return new ApiInfo("Checkout Service", "API for Checkout Service", "0.0.1", "Terms of service",
				new Contact("Contact", "mailto:heiderrivera@gmail.com", "heiderrivera@gmail.com"), "", "",
				Collections.emptyList());
	}
}
