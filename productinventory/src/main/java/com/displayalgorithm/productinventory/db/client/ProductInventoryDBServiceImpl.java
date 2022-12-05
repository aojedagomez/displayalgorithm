package com.displayalgorithm.productinventory.db.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.displayalgorithm.productinventory.db.model.Product;
import com.displayalgorithm.productinventory.db.model.Size;
import com.displayalgorithm.productinventory.db.model.Stock;

@Service
public class ProductInventoryDBServiceImpl implements ProductInventoryDBService {

	@Value("${datasource.productcsv}")
	private String productcsv;

	@Value("${datasource.sizecsv}")
	private String sizecsv;

	@Value("${datasource.stockcsv}")
	private String stockcsv;

	public String getProductcsv() {
		return productcsv;
	}

	public String getSizecsv() {
		return sizecsv;
	}

	public String getStockcsv() {
		return stockcsv;
	}

	@Override
	public List<Product> listProducts() {
		List<Product> products = readProductsFromCSV(getProductcsv());
		loadSizesFromCSV(getSizecsv(), products);
		loadStockFromCSV(getStockcsv(), products);
		return products;
	}

	/**
	 * Método para leer la información del csv.
	 * 
	 * @param fileName
	 * @return
	 */
	private static List<Product> readProductsFromCSV(String fileName) {

		List<Product> products = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader bufferedReader = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			String line = bufferedReader.readLine();

			while (line != null) {
				String[] attributes = line.split(", ");
				Product product = createProduct(attributes);
				products.add(product);
				line = bufferedReader.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return products;
	}

	/**
	 * Método para crear productos en base a los datos del csv.
	 * 
	 * @param metadata
	 * @return
	 */
	private static Product createProduct(String[] metadata) {
		int id = Integer.parseInt(metadata[0].replaceAll("\\s+", ""));
		int sequence = Integer.parseInt(metadata[1].replaceAll("\\s+", ""));
		return new Product(id, sequence);
	}

	/**
	 * Método par cargar las tallas en los productos.
	 * 
	 * @param fileName
	 * @param products
	 */
	private static void loadSizesFromCSV(String fileName, List<Product> products) {

		Path pathToFile = Paths.get(fileName);
		try (BufferedReader bufferedReader = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			String line = bufferedReader.readLine();

			while (line != null) {
				String[] attributes = line.split(", ");

				int id = Integer.parseInt(attributes[0].replaceAll("\\s+", ""));
				int productId = Integer.parseInt(attributes[1].replaceAll("\\s+", ""));
				String backSoon = attributes[2].replaceAll("\\s+", "");
				String special = attributes[3].replaceAll("\\s+", "");
				Size size = new Size(id, Boolean.parseBoolean(backSoon), Boolean.parseBoolean(special));
				for (Product product : products) {
					if (productId == product.getId()) {
						product.getSize().add(size);
						break;
					}
				}
				line = bufferedReader.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Método para cargar el stock de las tallas.
	 * 
	 * @param fileName
	 * @param products
	 */
	private static void loadStockFromCSV(String fileName, List<Product> products) {

		Path pathToFile = Paths.get(fileName);
		try (BufferedReader bufferedReader = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			String line = bufferedReader.readLine();

			while (line != null) {
				String[] attributes = line.split(", ");
				int sizeId = Integer.parseInt(attributes[0].replaceAll("\\s+", ""));
				int quantity = Integer.parseInt(attributes[1].replaceAll("\\s+", ""));
				for (Product product : products) {
					for (Size size : product.getSize()) {
						if (sizeId == size.getId()) {
							Stock stock = new Stock(quantity);
							size.setStock(stock);
							break;
						}
					}
				}
				line = bufferedReader.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}