package org.horivera.codingchallenge.bill.dto;

import org.horivera.codingchallenge.bill.domain.BillStatus;

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
