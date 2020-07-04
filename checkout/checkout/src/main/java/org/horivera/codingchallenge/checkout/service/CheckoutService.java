package org.horivera.codingchallenge.checkout.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.dozer.DozerBeanMapper;
import org.horivera.codingchallenge.checkout.domain.Order;
import org.horivera.codingchallenge.checkout.domain.OrderStatus;
import org.horivera.codingchallenge.checkout.dto.BillDTO;
import org.horivera.codingchallenge.checkout.dto.BillOutDTO;
import org.horivera.codingchallenge.checkout.dto.OrderDTO;
import org.horivera.codingchallenge.checkout.dto.OrderOutDTO;
import org.horivera.codingchallenge.checkout.dto.ProductDTO;
import org.horivera.codingchallenge.checkout.dto.SentOrderDTO;
import org.horivera.codingchallenge.checkout.dto.SentOrderOutDTO;
import org.horivera.codingchallenge.checkout.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CheckoutService {

	@Autowired
	private CheckoutRepository checkoutRepository;

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	public OrderOutDTO checkout(OrderDTO orderDTO) {

		Order order = mapper.map(orderDTO, Order.class);
		order.setId(UUID.randomUUID().toString());
		order.setStatus(OrderStatus.CREATED);

		BillDTO billDTO = BillDTO.builder().clientId(orderDTO.getClientId()).orderId(order.getId())
				.direction(orderDTO.getDirection()).products(orderDTO.getProducts()).build();
		Optional<BillOutDTO> bill = consumeCreateBill(billDTO);

		if (bill.isPresent()) {
			order.setBillId(bill.get().getBillId());
			order.setStatus(OrderStatus.BILLED);

			createSentOrder(order, orderDTO.getProducts());
		} else {
			order.setStatus(OrderStatus.CANCELED);
		}

		checkoutRepository.save(order);

		return OrderOutDTO.builder().orderId(order.getId()).status(order.getStatus()).build();
	}

	private void createSentOrder(Order order, List<ProductDTO> products) {
		SentOrderDTO sentOrderDTO = SentOrderDTO.builder().billId(order.getBillId()).clientId(order.getClientId())
				.orderId(order.getId()).direction(order.getDirection()).products(products).build();
		Optional<SentOrderOutDTO> sentOrder = consumeCreateSentOrder(sentOrderDTO);

		if (sentOrder.isPresent()) {
			order.setSentOrderId(sentOrder.get().getSentOrderId());
			order.setStatus(OrderStatus.SENT_ORDER_CREATED);
		} else {
			cancelBill(order);
		}
	}

	private void cancelBill(Order order) {
		if (consumeCancelBill(order.getBillId())) {
			order.setStatus(OrderStatus.BILL_CANCELED);
		} else {
			order.setStatus(OrderStatus.REQUIRE_BILL_CANCEL);
		}
	}

	private Optional<BillOutDTO> consumeCreateBill(BillDTO billDTO) {
		Optional<BillOutDTO> response = Optional.empty();
		try {
			response = Optional.of(restTemplate.postForObject(environment.getProperty("bill-service.create"), billDTO,
					BillOutDTO.class));
		} catch (Exception e) {
			log.error("Error in bill creation", e);
		}
		return response;
	}

	private Optional<SentOrderOutDTO> consumeCreateSentOrder(SentOrderDTO sentOrderDTO) {
		Optional<SentOrderOutDTO> response = Optional.empty();
		try {
			response = Optional.of(restTemplate.postForObject(environment.getProperty("logistic-service.create"),
					sentOrderDTO, SentOrderOutDTO.class));
		} catch (Exception e) {
			log.error("Error in bill creation", e);
		}
		return response;
	}

	private boolean consumeCancelBill(String billId) {
		boolean response = true;
		try {
			restTemplate.put(environment.getProperty("bill-service.cancel"), null, billId);
		} catch (Exception e) {
			log.error("Error in bill cancelation", e);
			response = false;
		}
		return response;
	}

}
