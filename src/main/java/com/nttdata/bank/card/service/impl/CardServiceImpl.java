package com.nttdata.bank.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.json.JsonMapper;
//import com.nttdata.bank.card.client.CustomerRest;
import com.nttdata.bank.card.client.ProductRest;
import com.nttdata.bank.card.dto.CardDTO;
import com.nttdata.bank.card.model.Card;
import com.nttdata.bank.card.repository.CardRepository;
import com.nttdata.bank.card.service.CardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CardServiceImpl implements CardService{
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private ProductRest productRest;
	
	/*@Autowired 
	private CustomerRest customerRest;*/
	
	@Autowired
	private JsonMapper jsonMapper;
	
	@Override
	public Flux<Card> showCardInformationByCustomerId(String customerId) {
		return cardRepository.findCardByCustomerId(customerId);
	}

	@Override
	public Flux<CardDTO> findByCustomerId(String customerId) {
		Flux<CardDTO> cardDTO = cardRepository.findCardByCustomerId(customerId).map(c -> convertirACardDTO(c));
		return cardDTO.flatMap(card -> 
					Mono.just(card)
					.zipWith(productRest.findById(card.getProductId())
							,(c, p) -> {
								c.setProduct(p);
								return c;
							}));
	}

	@Override
	public Mono<Card> save(Card card) {
		return cardRepository.save(card);
	}
	
	@Override
	public Mono<Card> associateAccount(String id, String accountId) {
		String tarjetaDebito = "628ec6d1f45016ce6d56424f";
		return cardRepository.findById(id)
				.flatMap(c -> {
					System.out.println(c);
					if(c.getProductId().equals(tarjetaDebito)) {
						if(c.getMainAccount() == null) {
							c.setMainAccount(accountId);
						} else {
							c.getAssociatedAccounts().add(accountId);
						}
					}else {
						/*Mono.just(c)
						.flatMap(ca -> customerRest.showCustomerInformationById(c.getCustomerId())
								.map(cu -> {
									if(cu.getType() == 1) {
										c.setMainAccount(accountId);
									} else {
										c.getAssociatedAccounts().add(accountId);
									}
									return ca;
								}));*/
						c.getAssociatedAccounts().add(accountId);
					}
					return cardRepository.save(c);
				});
	}
	
	
	/*Casteo de objetos usando JsonMapper*/
	private CardDTO convertirACardDTO(Card card) {
		return jsonMapper.convertValue(card, CardDTO.class);
	}

}
