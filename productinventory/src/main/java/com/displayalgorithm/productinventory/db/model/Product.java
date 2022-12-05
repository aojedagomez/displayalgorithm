package com.displayalgorithm.productinventory.db.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Product {

	private int id;
	private int sequence;
	private List<Size> size = null;

	public Product(int id, int sequence) {
		this.id = id;
		this.sequence = sequence;
		this.size = new ArrayList<>();
	}
}
