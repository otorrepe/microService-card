package com.nttdata.bank.card.client.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Product {
	
	@Id
	private String _id;
	
	private String name;
	
	private Byte type;
	
	private Byte category;
	
	private String description;
	
	private Float commissionMaintenance;
	
	private Byte maxTransactions;
	
	private Float commissionTransaction;

}
