package org.horivera.codingchallenge.logistic.domain;

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
public class SentOrder {

	@Id
	private String id;
	private SentOrderStatus status;
	private Date date;
	private Long clientId;
	private String orderId;
	private String billId;
	private String direction;
	private List<Product> products;
}
