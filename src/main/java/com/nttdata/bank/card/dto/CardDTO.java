package com.nttdata.bank.card.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.nttdata.bank.card.client.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CardDTO {
	
	private String _id;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String customerId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String productId;
	
	private String mainAccount;
	
	private List<String> associatedAccounts;
	
	private Product product;
	
	private String createAt;
	
	/*Tarjeta credito*/
	private Double amount;
	
	private Double amountAvailable;

}
