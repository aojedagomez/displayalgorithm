package com.displayalgorithm.productinventory.service.impl;

import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.displayalgorithm.productinventory.db.client.ProductInventoryDBService;
import com.displayalgorithm.productinventory.db.model.Product;
import com.displayalgorithm.productinventory.db.model.Size;
import com.displayalgorithm.productinventory.service.ProductInventoryService;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

	private ProductInventoryDBService productInventoryDBService;

	@Autowired
	public ProductInventoryServiceImpl(ProductInventoryDBService productInventoryDBService) {
		this.productInventoryDBService = productInventoryDBService;
	}

	@Override
	public String listProducts() {
		Set<Product> products = productInventoryDBService.listProducts();
		TreeMap<Integer, Integer> productList = new TreeMap<>();
		for (Product product : products) {
			if (isVisible(product.getSize())) {
				productList.put(product.getSequence(), product.getId());
			}
		}
		productList.descendingKeySet();
		return productList.values().toString().replaceAll("[\\p{Ps}\\p{Pe}]", "");
	}

	/**
	 * Este método comprueba so hay stock de una talla.
	 * 
	 * @param size
	 * @return
	 */
	public boolean hasStock(Size size) {
		return size.getBackSoon() || (size.getStock() != null && size.getStock().getQuantity() > 0);
	}

	/**
	 * Este método comprueba si un producto es visible o no.
	 * 
	 * @param sizes
	 * @return
	 */
	public boolean isVisible(Set<Size> sizes) {
		boolean specialWithStock = false;
		boolean nonSpecialWithStock = false;
		boolean specialProduct = false;
		for (Size size : sizes) {

			if (size.getSpecial()) {
				specialProduct = size.getSpecial();
				specialWithStock = hasStock(size);
			}

			if (!size.getSpecial()) {
				nonSpecialWithStock = hasStock(size);
			}
		}

		return (specialProduct && specialWithStock && nonSpecialWithStock) || (!specialProduct && nonSpecialWithStock);

	}

}