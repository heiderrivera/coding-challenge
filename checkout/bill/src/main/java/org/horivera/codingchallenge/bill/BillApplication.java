package org.horivera.codingchallenge.bill;

import org.horivera.codingchallenge.bill.conf.MapperConfig;
import org.horivera.codingchallenge.bill.conf.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(value = { SwaggerConfig.class, MapperConfig.class })
@SpringBootApplication
public class BillApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillApplication.class, args);
	}

}
