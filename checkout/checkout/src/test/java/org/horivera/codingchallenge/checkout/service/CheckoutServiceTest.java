package org.horivera.codingchallenge.checkout.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.dozer.DozerBeanMapper;
import org.horivera.codingchallenge.checkout.domain.Order;
import org.horivera.codingchallenge.checkout.domain.OrderStatus;
import org.horivera.codingchallenge.checkout.dto.BillDTO;
import org.horivera.codingchallenge.checkout.dto.BillOutDTO;
import org.horivera.codingchallenge.checkout.dto.OrderDTO;
import org.horivera.codingchallenge.checkout.dto.OrderOutDTO;
import org.horivera.codingchallenge.checkout.dto.SentOrderDTO;
import org.horivera.codingchallenge.checkout.dto.SentOrderOutDTO;
import org.horivera.codingchallenge.checkout.repository.CheckoutRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceTest {

	private static final String URL_BILL_CANCEL = "http://localhost:8082/bill/cancel/{billId}";

	private static final String URL_LOGISTIC_CREATE = "http://localhost:8083/logistic/create";

	private static final String URL_BILL_CREATE = "http://localhost:8082/bill/create";

	@Mock
	private CheckoutRepository checkoutRepository;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private DozerBeanMapper mapper;

	@Mock
	private Environment environment;

	@InjectMocks
	private CheckoutService checkOutService;
	
	@Before
	public void initEnvironment() {
		Mockito.when(environment.getProperty("bill-service.create")).thenReturn(URL_BILL_CREATE);
		Mockito.when(environment.getProperty("bill-service.cancel")).thenReturn(URL_BILL_CANCEL);
		Mockito.when(environment.getProperty("logistic-service.create")).thenReturn(URL_LOGISTIC_CREATE);
	}

	@Test
	public void checkout_WhenConsumeCreateBillFail_ThenOrderStatusCanceled() {

		OrderDTO orderDTO = OrderDTO.builder().build();
		Mockito.when(mapper.map(orderDTO, Order.class)).thenReturn(Order.builder().build());
		Mockito.when(restTemplate.postForObject(Mockito.eq(URL_BILL_CREATE),
				Mockito.any(BillDTO.class), Mockito.any())).thenThrow(HttpClientErrorException.class);

		OrderOutDTO orderOutDTO = checkOutService.checkout(orderDTO);

		Mockito.verify(mapper, Mockito.times(1)).map(orderDTO, Order.class);
		Mockito.verify(restTemplate, Mockito.times(1)).postForObject(Mockito.eq(URL_BILL_CREATE),
				Mockito.any(BillDTO.class), Mockito.any());
		Mockito.verify(checkoutRepository, Mockito.times(1)).save(Mockito.any(Order.class));
		assertEquals(OrderStatus.CANCELED, orderOutDTO.getStatus());
	}

	@Test
	public void checkout_WhenCreateBillAndCreateSentOrder_ThenOrderStatusSentOrderCreated() {

		OrderDTO orderDTO = OrderDTO.builder().build();
		Mockito.when(mapper.map(orderDTO, Order.class)).thenReturn(Order.builder().build());
		Mockito.when(restTemplate.postForObject(Mockito.eq(URL_BILL_CREATE),
				Mockito.any(BillDTO.class), Mockito.any())).thenReturn(BillOutDTO.builder().build());
		Mockito.when(restTemplate.postForObject(Mockito.eq(URL_LOGISTIC_CREATE),
				Mockito.any(SentOrderDTO.class), Mockito.any())).thenReturn(SentOrderOutDTO.builder().build());

		OrderOutDTO orderOutDTO = checkOutService.checkout(orderDTO);

		Mockito.verify(mapper, Mockito.times(1)).map(orderDTO, Order.class);
		Mockito.verify(restTemplate, Mockito.times(1)).postForObject(Mockito.eq(URL_BILL_CREATE),
				Mockito.any(BillDTO.class), Mockito.any());
		Mockito.verify(restTemplate, Mockito.times(1)).postForObject(
				Mockito.eq(URL_LOGISTIC_CREATE), Mockito.any(SentOrderDTO.class), Mockito.any());
		Mockito.verify(checkoutRepository, Mockito.times(1)).save(Mockito.any(Order.class));
		assertEquals(OrderStatus.SENT_ORDER_CREATED, orderOutDTO.getStatus());
	}

	@Test
	public void checkout_WhenCreateBillAndConsumeCreateSentOrderFailAndCancelBill_ThenOrderStatusBillCanceled() {

		OrderDTO orderDTO = OrderDTO.builder().build();
		Mockito.when(mapper.map(orderDTO, Order.class)).thenReturn(Order.builder().build());
		Mockito.when(restTemplate.postForObject(Mockito.eq(URL_BILL_CREATE),
				Mockito.any(BillDTO.class), Mockito.any())).thenReturn(BillOutDTO.builder().build());
		Mockito.when(restTemplate.postForObject(Mockito.eq(URL_LOGISTIC_CREATE),
				Mockito.any(SentOrderDTO.class), Mockito.any())).thenThrow(HttpClientErrorException.class);

		OrderOutDTO orderOutDTO = checkOutService.checkout(orderDTO);

		Mockito.verify(mapper, Mockito.times(1)).map(orderDTO, Order.class);
		Mockito.verify(restTemplate, Mockito.times(1)).postForObject(Mockito.eq(URL_BILL_CREATE),
				Mockito.any(BillDTO.class), Mockito.any());
		Mockito.verify(restTemplate, Mockito.times(1)).postForObject(
				Mockito.eq(URL_LOGISTIC_CREATE), Mockito.any(SentOrderDTO.class), Mockito.any());
		Mockito.verify(restTemplate, Mockito.times(1)).put(Mockito.eq(URL_BILL_CANCEL), Mockito.any(),
				Mockito.nullable(String.class));
		Mockito.verify(checkoutRepository, Mockito.times(1)).save(Mockito.any(Order.class));
		assertEquals(OrderStatus.BILL_CANCELED, orderOutDTO.getStatus());
	}

	@Test
	public void checkout_WhenCreateBillAndConsumeCreateSentOrderFailAndCancelBillFail_ThenOrderStatusRequireBillCancel() {

		OrderDTO orderDTO = OrderDTO.builder().build();
		Mockito.when(mapper.map(orderDTO, Order.class)).thenReturn(Order.builder().build());
		Mockito.when(restTemplate.postForObject(Mockito.eq(URL_BILL_CREATE),
				Mockito.any(BillDTO.class), Mockito.any())).thenReturn(BillOutDTO.builder().build());
		Mockito.when(restTemplate.postForObject(Mockito.eq(URL_LOGISTIC_CREATE),
				Mockito.any(SentOrderDTO.class), Mockito.any())).thenThrow(HttpClientErrorException.class);
		Mockito.doThrow(HttpClientErrorException.class).when(restTemplate)
				.put(Mockito.eq(URL_BILL_CANCEL), Mockito.any(), Mockito.nullable(String.class));

		OrderOutDTO orderOutDTO = checkOutService.checkout(orderDTO);

		Mockito.verify(mapper, Mockito.times(1)).map(orderDTO, Order.class);
		Mockito.verify(restTemplate, Mockito.times(1)).postForObject(Mockito.eq(URL_BILL_CREATE),
				Mockito.any(BillDTO.class), Mockito.any());
		Mockito.verify(restTemplate, Mockito.times(1)).postForObject(
				Mockito.eq(URL_LOGISTIC_CREATE), Mockito.any(SentOrderDTO.class), Mockito.any());
		Mockito.verify(restTemplate, Mockito.times(1)).put(Mockito.eq(URL_BILL_CANCEL), Mockito.any(),
				Mockito.nullable(String.class));
		Mockito.verify(checkoutRepository, Mockito.times(1)).save(Mockito.any(Order.class));
		assertEquals(OrderStatus.REQUIRE_BILL_CANCEL, orderOutDTO.getStatus());
	}

}
