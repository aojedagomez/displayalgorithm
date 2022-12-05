package com.displayalgorithm.productinventory.db.model;

import lombok.Data;

@Data
public class Stock {

	private int quantity;

	public Stock(int quantity) {
		this.quantity = quantity;
	}
}
