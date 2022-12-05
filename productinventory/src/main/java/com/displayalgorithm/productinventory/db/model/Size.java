package com.displayalgorithm.productinventory.db.model;

import lombok.Data;

@Data
public class Size {

	private int id;
	private Boolean backSoon = null;
	private Boolean special = null;
	private Stock stock = null;

	public Size(int id, Boolean backSoon, Boolean special) {
		this.id = id;
		this.backSoon = backSoon;
		this.special = special;
	}
}
