package com.org.listener.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {

	private String name;
	
	private String id;
	
	private String location;
	
	private String inventoryType;
	
	private int quantity;
}
