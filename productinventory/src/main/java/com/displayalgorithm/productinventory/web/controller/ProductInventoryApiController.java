package com.displayalgorithm.productinventory.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.displayalgorithm.productinventory.service.ProductInventoryService;

@RestController
public class ProductInventoryApiController {
	
	private ProductInventoryService productInventoryService;
	
	@Autowired
	public ProductInventoryApiController(ProductInventoryService productInventoryService) {
		this.productInventoryService = productInventoryService;
	}
	
	@RequestMapping(value="/product", method = RequestMethod.GET)
	public String getCustomerInfo() {
		return productInventoryService.listProducts();
	}

}
