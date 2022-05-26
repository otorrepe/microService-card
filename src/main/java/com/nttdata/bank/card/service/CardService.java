package com.nttdata.bank.card.service;

import com.nttdata.bank.card.dto.CardDTO;
import com.nttdata.bank.card.model.Card;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CardService {
	
	Flux<CardDTO> findByCustomerId(String customerId);
	
	Mono<Card> save(Card card);
	
	Flux<Card> showCardInformationByCustomerId(String customerId);
	
	Mono<Card> associateAccount(String id, String accountId);

}
