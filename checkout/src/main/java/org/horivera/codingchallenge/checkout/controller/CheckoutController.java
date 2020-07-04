package org.horivera.codingchallenge.checkout.controller;

import javax.validation.Valid;

import org.horivera.codingchallenge.checkout.dto.OrderDTO;
import org.horivera.codingchallenge.checkout.dto.OrderOutDTO;
import org.horivera.codingchallenge.checkout.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CheckoutController {

	@Autowired
	private CheckoutService checkoutService;

	@PostMapping(path = "checkout", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OrderOutDTO checkout(@Valid @RequestBody OrderDTO orderDTO) {
		return checkoutService.checkout(orderDTO);
	}

}
