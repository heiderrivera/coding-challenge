package org.horivera.codingchallenge.checkout.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

	@NotNull
	private Long id;
	
	@NotNull
	@Positive
	private Long quantity;
	
	@NotNull
	@Positive
	private Double cost;

}
