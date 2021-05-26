package com.coffeecorner.service;

import com.coffeecorner.domain.Order;
import com.coffeecorner.domain.Product;

import java.util.List;
import java.util.Map;

/**
 * @author Mohamed.Benjelloun
 * The order service for managing orders
 */
public interface OrderService {

    /**
     *
     * @param menu map containing the products organized by id
     * @param selections the products selected by the client
     * @return order with selected items
     */
    Order createOrder(Map<Integer, Product> menu, List<Integer> selections);

    /**
     * process the order to calculate number of free extras, total beverages, total snacks, total amount
     * and discounts to apply
     * @param order with selected items, process the selected items, to calculate the totals
     */
    void processOrder(Order order);

    /**
     * print the invoice
     * @param order with selected items and the totals
     */
    void printInvoice(Order order);
}
