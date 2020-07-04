package org.horivera.codingchallenge.checkout.conf;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RestConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder, ObjectMapper objectMapper) {
		return builder.setConnectTimeout(Duration.ofSeconds(30)).setReadTimeout(Duration.ofSeconds(30)).build();
	}
}
