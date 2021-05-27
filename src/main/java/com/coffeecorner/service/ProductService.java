package com.coffeecorner.service;

import java.util.Map;
import com.coffeecorner.domain.Product;

/**
 * @author Mohamed.Benjelloun
 * contracts for the service that manages the products
 */
public interface ProductService {

    /**
     * Method that return a map of products grouped by id
     * @param jsonFile the name of the json file containing the products
     * @return map with products organized by id
     */
    Map<Integer, Product> getProducts(String jsonFile);
}
