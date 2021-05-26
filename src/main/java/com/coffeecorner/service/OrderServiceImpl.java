package com.coffeecorner.service;

import com.coffeecorner.common.Constants;
import com.coffeecorner.domain.Order;
import com.coffeecorner.domain.Product;
import com.coffeecorner.domain.ProductType;

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

                // Selected product is in the menu?
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

            // Beverage?
            if (product.getType().equals(ProductType.BEVERAGE)) {
                // Yes; count it
                totalBeverages++;

                // Per each 5 beverages, the 5th is free
                if (totalBeverages % Constants.STAMP_CARD_LIMIT == 0) {
                    freeBeverages.add(product);
                    System.out.println("discount before:" + discounts);
                    discounts += product.getPrice();
                    System.out.println("discount after:" + discounts);
                }
            }
            // Snack?
            if (product.getType().equals(ProductType.SNACK)) {
                // Yes; count it
                totalSnacks++;
            }
        }
        // Count free extras
        //nFreeExtras = ((totalBeverages + totalSnacks - Math.abs(totalBeverages - totalSnacks)) / 2);
        if (totalBeverages > 0 && totalSnacks > 0)
        {
            if(totalBeverages >= totalSnacks) {
                nFreeExtras = totalSnacks;
            } else {
                nFreeExtras = totalBeverages;
            }
        }

        System.out.println("nFreeExtras:" + nFreeExtras);

        // Add first <nFreeExtras> for free
        for (int i = 0; i < order.getProducts().size() && nFreeExtras > 0; i++) {
            System.out.println("product type:" + order.getProducts().get(i).getType());
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

        System.out.println("============================================");
        System.out.println("Thanks for visiting Charlene's Coffee Corner");
        System.out.println("============================================");
        System.out.println("-----------");
        System.out.println("| Invoice |");
        System.out.println("-----------");
        System.out.println("Ordered products:");
        for (Product product : order.getProducts()) {
            System.out.println("- " + product.toString());
        }
        System.out.println("----------------------------------");
        System.out.println("Free beverages:");
        if (order.getFreeBeverages().size() > 0) {
            for (Product product : order.getFreeBeverages()) {
                System.out.println("- " + product.toString());
            }
        } else {
            System.out.println("<NA>");
        }

        System.out.println("----------------------------------");
        System.out.println("Free freeExtras:");

        if (order.getFreeExtras().size() > 0) {
            for (Product product : order.getFreeExtras()) {
                System.out.println("- " + product.toString());
            }
        } else {
            System.out.println("<NA>");
        }
        System.out.println("----------------------------------");
        System.out.println("Resume:");
        System.out.println("Gross Amount:  " + format.format(order.getTotalAmount()));
        System.out.println("Discounts:   - " + format.format(order.getDiscounts()));
        System.out.println("Total to pay:  " + format.format(order.getAmountToPay()));
    }
}
