package com.displayalgorithm.productinventory.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.displayalgorithm.productinventory.db.client.ProductInventoryDBService;
import com.displayalgorithm.productinventory.db.model.Product;
import com.displayalgorithm.productinventory.db.model.Size;
import com.displayalgorithm.productinventory.db.model.Stock;
import com.displayalgorithm.productinventory.service.ProductInventoryService;

@SpringBootTest
class ProductInventoryServiceTest {

	@Mock
	private ProductInventoryDBService productInventoryDBService;

	@InjectMocks
	ProductInventoryService productInventoryService = new ProductInventoryServiceImpl(productInventoryDBService);

	@Test
	public void nonSpecialProductWithSockTest() {

		Set<Product> products = new HashSet<>();
		Product product = new Product(1, 1);
		Size size = new Size(11, false, false);
		Stock stock = new Stock(1);
		size.setStock(stock);
		product.getSize().add(size);
		products.add(product);
		String listedProducts = "";

		Mockito.when(productInventoryDBService.listProducts()).thenReturn(products);
		listedProducts = productInventoryService.listProducts();

		assertEquals("1", listedProducts);

	}

	@Test
	public void nonSpecialProductBackSoonTest() {

		Set<Product> products = new HashSet<>();
		Product product = new Product(1, 1);
		Size size = new Size(11, true, false);
		Stock stock = new Stock(0);
		size.setStock(stock);
		product.getSize().add(size);
		products.add(product);
		String listedProducts = "";

		Mockito.when(productInventoryDBService.listProducts()).thenReturn(products);
		listedProducts = productInventoryService.listProducts();

		assertEquals("1", listedProducts);

	}

	@Test
	public void nonSpecialProductWithOutSockTest() {

		Set<Product> products = new HashSet<>();
		Product product = new Product(1, 1);
		Size size = new Size(11, false, false);
		Stock stock = new Stock(0);
		size.setStock(stock);
		product.getSize().add(size);
		products.add(product);
		String listedProducts = "";

		Mockito.when(productInventoryDBService.listProducts()).thenReturn(products);
		listedProducts = productInventoryService.listProducts();

		assertEquals("", listedProducts);

	}

	@Test
	public void specialProductWithSockTest() {

		Set<Product> products = new HashSet<>();
		Product product = new Product(1, 1);
		Size specialSize = new Size(11, false, true);
		Size size = new Size(12, false, false);
		Stock stock = new Stock(1);
		size.setStock(stock);
		specialSize.setStock(stock);
		product.getSize().add(size);
		product.getSize().add(specialSize);
		products.add(product);
		String listedProducts = "";

		Mockito.when(productInventoryDBService.listProducts()).thenReturn(products);
		listedProducts = productInventoryService.listProducts();

		assertEquals("1", listedProducts);

	}

	@Test
	public void specialProductBackSoonTest() {

		Set<Product> products = new HashSet<>();
		Product product = new Product(1, 1);
		Size specialSize = new Size(11, true, true);
		Size size = new Size(12, true, false);
		Stock stock = new Stock(0);
		size.setStock(stock);
		specialSize.setStock(stock);
		product.getSize().add(size);
		product.getSize().add(specialSize);
		products.add(product);
		String listedProducts = "";

		Mockito.when(productInventoryDBService.listProducts()).thenReturn(products);
		listedProducts = productInventoryService.listProducts();

		assertEquals("1", listedProducts);

	}

	@Test
	public void specialProductWithOutSockTest() {

		Set<Product> products = new HashSet<>();
		Product product = new Product(1, 1);
		Size size = new Size(11, false, true);
		Stock stock = new Stock(1);
		size.setStock(stock);
		product.getSize().add(size);
		products.add(product);
		String listedProducts = "";

		Mockito.when(productInventoryDBService.listProducts()).thenReturn(products);
		listedProducts = productInventoryService.listProducts();

		assertEquals("", listedProducts);

	}

	@Test
	public void nonSpecialProductNullSockTest() {

		Set<Product> products = new HashSet<>();
		Product product = new Product(1, 1);
		Size size = new Size(11, false, true);
		product.getSize().add(size);
		products.add(product);
		String listedProducts = "";

		Mockito.when(productInventoryDBService.listProducts()).thenReturn(products);
		listedProducts = productInventoryService.listProducts();

		assertEquals("", listedProducts);

	}
}
