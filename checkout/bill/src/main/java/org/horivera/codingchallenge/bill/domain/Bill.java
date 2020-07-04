package org.horivera.codingchallenge.bill.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {

	@Id
	private String id;
	private BillStatus status;
	private Date date;
	private Long clientId;
	private String orderId;
	private String direction;
	private List<Product> products;
	private Double sum;
}
