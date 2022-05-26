package com.nttdata.bank.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bank.card.dto.CardDTO;
import com.nttdata.bank.card.model.Card;
import com.nttdata.bank.card.service.CardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CardController {

	@Autowired
	private CardService cardService;
	
	@GetMapping("/card")
	public Flux<Card> showCardInformationByCustomerId(@RequestParam String customerId){
		return cardService.showCardInformationByCustomerId(customerId);
	}
	
	@GetMapping
	public Flux<CardDTO> findByCustomerId(@RequestParam String customerId){
		return cardService.findByCustomerId(customerId);
	}
	
	@PostMapping
	public Mono<Card> save(@RequestBody Card card,
							@RequestParam String customerId,
							@RequestParam String productId){
		card.setCustomerId(customerId);
		card.setProductId(productId);
		return cardService.save(card);
	}
	
	@PutMapping("/card")
	public Mono<Card> associateAccount(@RequestParam String id, 
										@RequestParam String accountId){
		return cardService.associateAccount(id, accountId);
	}
	
}
