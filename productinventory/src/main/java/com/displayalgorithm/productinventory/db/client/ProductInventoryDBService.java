package com.displayalgorithm.productinventory.db.client;

import java.util.Set;

import com.displayalgorithm.productinventory.db.model.Product;

/**
 * Esta interfaz, junto con su implementción, simulan lo que sería una implementación de un cliente para consumir un servicio externo.
 * 
 * @author Admin
 *
 */
public interface ProductInventoryDBService {

	/**
	 * Este método se utiliza para recuperar los productos almacenados en base de datos.
	 * 
	 * @return
	 */
	Set<Product> listProducts();
}