package com.displayalgorithm.productinventory.db.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.displayalgorithm.productinventory.db.model.Product;
import com.displayalgorithm.productinventory.db.model.Size;
import com.displayalgorithm.productinventory.db.model.Stock;

@SpringBootTest
class ProductinventoryInitializationsTest {

	@Autowired
	private ProductInventoryDBServiceImpl productInventoryDBService = new ProductInventoryDBServiceImpl();

	@Test
	public void nonSpecialProductWithSockTest() {
		List<Product> products = new ArrayList<>();
		Product product = new Product(1, 10);
		Size size = new Size(11, true, false);
		Stock stock = new Stock(0);
		size.setStock(stock);
		product.getSize().add(size);
		products.add(product);

		List<Product> dbResponse = productInventoryDBService.listProducts();
		assertEquals(products, dbResponse);

	}
}
