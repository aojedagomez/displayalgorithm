package com.displayalgorithm.productinventory.db.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Product {

	private int id;
	private int sequence;
	private Set<Size> size = null;

	public Product(int id, int sequence) {
		this.id = id;
		this.sequence = sequence;
		this.size = new HashSet<>();
	}
}
