package org.horivera.codingchallenge.logistic.dto;

import org.horivera.codingchallenge.logistic.domain.SentOrderStatus;

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
