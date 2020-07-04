package org.horivera.codingchallenge.bill.conf;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

	@Bean
	public DozerBeanMapper modelMapper() {
		DozerBeanMapper modelMapper = new DozerBeanMapper();
		return modelMapper;
	}
}
