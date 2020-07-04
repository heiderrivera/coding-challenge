package org.horivera.codingchallenge.bill.controller;

import javax.validation.Valid;

import org.horivera.codingchallenge.bill.dto.BillDTO;
import org.horivera.codingchallenge.bill.dto.BillOutDTO;
import org.horivera.codingchallenge.bill.service.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bill")
public class BillController {

	@Autowired
	private BillService billService;

	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BillOutDTO create(@Valid @RequestBody BillDTO billDTO) {
		log.info("bill/create requestBody: {}", billDTO);
		BillOutDTO billOutDTO = billService.create(billDTO);
		log.info("bill/create responseBody: {}", billOutDTO);
		return billOutDTO;
	}

	@PutMapping(path = "/cancel/{billId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void cancel(@PathVariable("billId") String billId) {
		log.info("bill/cancel requestBody: {}", billId);
		billService.cancel(billId);
		log.info("bill/cancel Bill canceled {}", billId);
	}

}
