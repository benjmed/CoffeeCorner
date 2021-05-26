package com.coffeecorner.service;

import com.coffeecorner.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Mohamed.Benjelloun
 * The selection service for managing products selected by the customer
 */
public interface SelectionService {

    /**
     *
     * @param availableSelections available products
     * @return list of options to choose
     */
    List<Integer> readSelections(Set<Integer> availableSelections);

    /**
     * print the products
     * @param products map with products organized by id
     */
    void printMenu(Map<Integer, Product> products);
}
