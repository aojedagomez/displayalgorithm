package com.displayalgorithm.productinventory.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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

		List<Product> products = new ArrayList<>();
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

		List<Product> products = new ArrayList<>();
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

		List<Product> products = new ArrayList<>();
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

		List<Product> products = new ArrayList<>();
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

		List<Product> products = new ArrayList<>();
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

		List<Product> products = new ArrayList<>();
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

		List<Product> products = new ArrayList<>();
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
