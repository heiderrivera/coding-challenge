package org.horivera.codingchallenge.bill.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.horivera.codingchallenge.bill.domain.Bill;
import org.horivera.codingchallenge.bill.domain.BillStatus;
import org.horivera.codingchallenge.bill.domain.Product;
import org.horivera.codingchallenge.bill.dto.BillDTO;
import org.horivera.codingchallenge.bill.dto.BillOutDTO;
import org.horivera.codingchallenge.bill.dto.ProductDTO;
import org.horivera.codingchallenge.bill.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private DozerBeanMapper mapper;

	public BillOutDTO create(BillDTO billDTO) {
		Double sum = calculateBillSum(billDTO.getProducts());
		Bill bill = Bill.builder().id(UUID.randomUUID().toString()).status(BillStatus.CREATED)
				.clientId(billDTO.getClientId()).orderId(billDTO.getOrderId()).direction(billDTO.getDirection())
				.sum(sum).date(new Date()).products(map(billDTO.getProducts())).build();
		bill = billRepository.save(bill);
		return BillOutDTO.builder().billId(bill.getId()).status(bill.getStatus()).build();
	}

	private double calculateBillSum(List<ProductDTO> products) {
		return products.stream().mapToDouble(productDTO -> productDTO.getQuantity() * productDTO.getCost()).sum();
	}

	private List<Product> map(List<ProductDTO> products) {
		return products.stream().map(productDTO -> mapper.map(productDTO, Product.class)).collect(Collectors.toList());
	}

	public void cancel(String billId) {
		Bill bill = billRepository.findById(billId).get();
		bill.setStatus(BillStatus.CANCELED);
		bill = billRepository.save(bill);
	}
}
