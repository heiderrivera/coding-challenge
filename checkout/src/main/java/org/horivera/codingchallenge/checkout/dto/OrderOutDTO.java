package org.horivera.codingchallenge.checkout.dto;

import org.horivera.codingchallenge.checkout.domain.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderOutDTO {
	
	private String orderId;
	private OrderStatus status;
}
