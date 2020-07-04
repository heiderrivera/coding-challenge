package org.horivera.codingchallenge.checkout.dto;

import org.horivera.codingchallenge.checkout.domain.BillStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillOutDTO {
	
	private String billId;
	private BillStatus status;
}
