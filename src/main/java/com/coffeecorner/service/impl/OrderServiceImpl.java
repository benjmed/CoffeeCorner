package com.coffeecorner.service.impl;

import com.coffeecorner.common.Constants;
import com.coffeecorner.common.Loggers;
import com.coffeecorner.domain.Order;
import com.coffeecorner.domain.Product;
import com.coffeecorner.domain.ProductType;
import com.coffeecorner.service.OrderService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Mohamed.Benjelloun
 * Implementation of the service OrderService
 */
public class OrderServiceImpl implements OrderService {

    public Order createOrder(Map<Integer, Product> menu, List<Integer> selections) {
        Order order = null;
        List<Product> selectedProducts = new ArrayList<>();

        if (selections != null) {
            for (Integer selection : selections) {
                // Retrieves the product mapped to the selection
                Product product = menu.get(selection);

                // Selected product is in the menu
                if (product != null) {
                    // Yes; add selected product
                    selectedProducts.add(product);
                }
            }

            if (selectedProducts.size() > 0) {
                // Set products into the order
                order = new Order(selectedProducts);
            }
        }

        return order;
    }

    public void processOrder(Order order) {
        int nFreeExtras = 0;
        int totalBeverages = 0;
        int totalSnacks = 0;
        Double totalAmount = 0.0;
        Double discounts  = 0.0;
        List<Product> freeBeverages = new ArrayList<>();
        List<Product> freeExtras = new ArrayList<>();

        for (Product product : order.getProducts()) {
            // Total bill
            totalAmount += product.getPrice();

            // Beverage
            if (product.getType().equals(ProductType.BEVERAGE)) {
                // Yes; count it
                totalBeverages++;

                // Per each 5 beverages, the 5th is free
                if (totalBeverages % Constants.STAMP_CARD_LIMIT == 0) {
                    freeBeverages.add(product);
                    discounts += product.getPrice();
                }
            }
            // Snack
            if (product.getType().equals(ProductType.SNACK)) {
                // Yes; count it
                totalSnacks++;
            }
        }
        // Count free extras
        if (totalBeverages > 0 && totalSnacks > 0)
        {
            if(totalBeverages >= totalSnacks) {
                nFreeExtras = totalSnacks;
            } else {
                nFreeExtras = totalBeverages;
            }
        }

        // Add first <nFreeExtras> for free
        for (int i = 0; i < order.getProducts().size() && nFreeExtras > 0; i++) {
            if (order.getProducts().get(i).getType().equals(ProductType.EXTRA)) {
                nFreeExtras--;
                freeExtras.add(order.getProducts().get(i));
                discounts += order.getProducts().get(i).getPrice();
            }
        }

        // Set order info
        order.setFreeBeverages(freeBeverages);
        order.setFreeExtras(freeExtras);
        order.setTotalAmount(totalAmount);
        order.setDiscounts(discounts);
        order.setAmountToPay(totalAmount - discounts);
    }

    public void printInvoice(Order order) {

        DecimalFormat format = new DecimalFormat(Constants.DECIMAL_FORMAT_2_DIGITS);
        String currency;
        Loggers.log("============================================");
        Loggers.log("Thanks for visiting Charlene's Coffee Corner");
        Loggers.log("============================================");
        Loggers.log("-----------");
        Loggers.log("| Invoice |");
        Loggers.log("-----------");
        Loggers.log("Ordered products:");

        order.getProducts().forEach((final Product product)-> Loggers.log("- " + product.toString()));
        currency = order.getProducts().get(0).getCurrency();
        Loggers.log("----------------------------------");
        Loggers.log("Free beverages:");

        if (order.getFreeBeverages().size() > 0) {
            order.getFreeBeverages().forEach((final Product product)-> Loggers.log("- " + product.toString()));
        } else {
            Loggers.log("<NA>");
        }

        Loggers.log("----------------------------------");
        Loggers.log("Free freeExtras:");

        if (order.getFreeExtras().size() > 0) {
            order.getFreeExtras().forEach((final Product product)-> Loggers.log("- " + product.toString()));
        } else {
            Loggers.log("<NA>");
        }
        Loggers.log("----------------------------------");
        Loggers.log("Gross Amount:  " + format.format(order.getTotalAmount()) + " " + currency);
        Loggers.log("Discounts:   - " + format.format(order.getDiscounts())  + " " + currency);
        Loggers.log("Total to pay:  " + format.format(order.getAmountToPay())  + " " + currency);
    }
}
