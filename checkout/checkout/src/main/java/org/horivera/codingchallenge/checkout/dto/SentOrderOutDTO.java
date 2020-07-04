package org.horivera.codingchallenge.checkout.dto;

import org.horivera.codingchallenge.checkout.domain.SentOrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SentOrderOutDTO {
	
	private String sentOrderId;
	private SentOrderStatus status;
}
