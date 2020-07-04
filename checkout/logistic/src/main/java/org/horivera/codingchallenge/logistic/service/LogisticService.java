package org.horivera.codingchallenge.logistic.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.horivera.codingchallenge.logistic.domain.Product;
import org.horivera.codingchallenge.logistic.domain.SentOrder;
import org.horivera.codingchallenge.logistic.domain.SentOrderStatus;
import org.horivera.codingchallenge.logistic.dto.ProductDTO;
import org.horivera.codingchallenge.logistic.dto.SentOrderDTO;
import org.horivera.codingchallenge.logistic.dto.SentOrderOutDTO;
import org.horivera.codingchallenge.logistic.repository.LogisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticService {

	@Autowired
	private LogisticRepository logisticRepository;

	@Autowired
	private DozerBeanMapper mapper;

	public SentOrderOutDTO create(SentOrderDTO sentOrderDTO) {
		SentOrder sentOrder = SentOrder.builder().id(UUID.randomUUID().toString()).status(SentOrderStatus.CREATED)
				.date(new Date()).clientId(sentOrderDTO.getClientId()).orderId(sentOrderDTO.getOrderId())
				.billId(sentOrderDTO.getBillId()).direction(sentOrderDTO.getDirection()).products(map(sentOrderDTO.getProducts()))
				.build();
		sentOrder = logisticRepository.save(sentOrder);
		return SentOrderOutDTO.builder().sentOrderId(sentOrder.getId()).status(sentOrder.getStatus()).build();
	}

	private List<Product> map(List<ProductDTO> products) {
		return products.stream().map(productDTO -> mapper.map(productDTO, Product.class)).collect(Collectors.toList());
	}

}
