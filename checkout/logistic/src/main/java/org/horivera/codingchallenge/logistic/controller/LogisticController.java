package org.horivera.codingchallenge.logistic.controller;

import javax.validation.Valid;

import org.horivera.codingchallenge.logistic.dto.SentOrderOutDTO;
import org.horivera.codingchallenge.logistic.dto.SentOrderDTO;
import org.horivera.codingchallenge.logistic.service.LogisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/logistic")
public class LogisticController {

	@Autowired
	private LogisticService logisticService;

	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SentOrderOutDTO create(@Valid @RequestBody SentOrderDTO sentOrderDTO) {
		log.info("logistic/create requestBody: {}", sentOrderDTO);
		SentOrderOutDTO sentOrderOutDTO = logisticService.create(sentOrderDTO);
		log.info("logistic/create responseBody: {}", sentOrderOutDTO);
		return sentOrderOutDTO;
	}

}
