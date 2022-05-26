package com.nttdata.bank.card.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bank.card.model.Card;

import reactor.core.publisher.Flux;

@Repository
public interface CardRepository extends ReactiveMongoRepository<Card, String>{
	
	Flux<Card> findCardByCustomerId(String customerId);

}
