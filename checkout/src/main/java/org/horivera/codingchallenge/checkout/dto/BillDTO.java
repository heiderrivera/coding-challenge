package org.horivera.codingchallenge.checkout.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillDTO {

	@NotNull
	private Long clientId;
	
	@NotBlank
	private String orderId;
	
	@NotBlank
	private String direction;
	
	@NotEmpty
	private List<@Valid ProductDTO> products;
}
