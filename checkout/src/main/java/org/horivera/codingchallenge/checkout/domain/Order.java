package org.horivera.codingchallenge.checkout.domain;

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
public class Order {

	@Id
	private String id;
	private OrderStatus status;
	private String billId;
	private String SentOrderId;
	private Date date;
	private Long clientId;
	private String direction;
	private List<Product> products;
}