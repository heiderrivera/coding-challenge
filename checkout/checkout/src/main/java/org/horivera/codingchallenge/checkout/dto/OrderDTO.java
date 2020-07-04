package org.horivera.codingchallenge.checkout.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
	private Long clientId;
	private Date date;
	private String direction;
	private List<ProductDTO> products;
}
