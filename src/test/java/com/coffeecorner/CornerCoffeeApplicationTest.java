package com.coffeecorner;

import static org.junit.Assert.assertEquals;


import com.coffeecorner.common.Constants;
import com.coffeecorner.common.Loggers;
import com.coffeecorner.domain.Order;
import com.coffeecorner.domain.Product;
import com.coffeecorner.service.*;
import com.coffeecorner.service.impl.FileResourcesServiceImpl;
import com.coffeecorner.service.impl.OrderServiceImpl;
import com.coffeecorner.service.impl.ProductServiceImpl;
import com.coffeecorner.service.impl.SelectionServiceImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple CornerCoffeeApplication.
 */
public class CornerCoffeeApplicationTest
{
    FileResourcesService fileResourcesService;

    ProductService productService;

    SelectionService selectionService;

    private OrderService orderService;

    private Map<Integer, Product> products;

    @Before
    public void setUp() {
        Loggers.log("===================================");
        Loggers.log("====Integration tests==============");
        Loggers.log("===================================");
        fileResourcesService = new FileResourcesServiceImpl();
        productService = new ProductServiceImpl(fileResourcesService);
        orderService = new OrderServiceImpl();
        products = productService.getProducts(Constants.PRODUCTS_FILE);
        selectionService = new SelectionServiceImpl();
        selectionService.printMenu(products);
    }

    @Test
    public void shouldProcessOrderWithOneSmallCoffeeAndOneExtra() {
        Loggers.log("===================================");
        Loggers.log("Integration test: The client choose one small coffee with extra milk, " +
                "then the total amount should be 2.8 CHF");
        Loggers.log("===================================");
        List<Integer> selections = new ArrayList<>();
        selections.add(Integer.valueOf("1"));
        selections.add(Integer.valueOf("6"));

        Order order = orderService.createOrder(products, selections);
        orderService.processOrder(order);
        orderService.printInvoice(order);

        assertEquals(order.getAmountToPay(), Double.valueOf("2.8"));
    }

    @Test
    public void shouldProcessOrderWithOneLargeCoffeeAndOneBaconRoll() {
        Loggers.log("===================================");
        Loggers.log("Integration test: The client choose one large coffee and one bacon roll, " +
                "then the total amount should be 8 CHF");
        Loggers.log("===================================");
        List<Integer> selections = new ArrayList<>();
        selections.add(Integer.valueOf("3"));
        selections.add(Integer.valueOf("4"));

        Order order = orderService.createOrder(products, selections);
        orderService.processOrder(order);
        orderService.printInvoice(order);

        assertEquals(order.getAmountToPay(), Double.valueOf("8"));
    }

    @Test
    public void shouldProcessOrderWithOneLargeCoffeeWithExtraMilkAndOneBaconRoll() {
        Loggers.log("===================================");
        Loggers.log("Integration test: The client choose one large coffee with extra milk and one bacon roll, " +
                "then the total amount should be 8 CHF");
        Loggers.log("===================================");
        List<Integer> selections = new ArrayList<>();
        selections.add(Integer.valueOf("3"));
        selections.add(Integer.valueOf("4"));
        selections.add(Integer.valueOf("6"));

        Order order = orderService.createOrder(products, selections);
        orderService.processOrder(order);
        orderService.printInvoice(order);

        assertEquals(order.getAmountToPay(), Double.valueOf("8"));
    }

    @Test
    public void shouldProcessOrderWithFourSmallCoffeeAndOneOrangeJuice() {
        Loggers.log("===================================");
        Loggers.log("Integration test: The client choose 4 small coffee and one Freshly squeezed orange juice, " +
                "then the total amount should be 10 CHF and the 5th beverage is for free");
        Loggers.log("===================================");
        List<Integer> selections = new ArrayList<>();
        selections.add(Integer.valueOf("1"));
        selections.add(Integer.valueOf("1"));
        selections.add(Integer.valueOf("1"));
        selections.add(Integer.valueOf("1"));
        selections.add(Integer.valueOf("5"));

        Order order = orderService.createOrder(products, selections);
        orderService.processOrder(order);
        orderService.printInvoice(order);

        assertEquals(order.getAmountToPay(), Double.valueOf("10"));
    }

    @Test
    public void shouldProcessOrderWithFiveSmallCoffeeAndSixMediumCoffee() {
        Loggers.log("===================================");
        Loggers.log("Integration test: The client choose 5 small coffee and 6 medium coffee, " +
                "then the 5th small coffee and the 10th medium coffee are for free, the total amount should be 25 CHF");
        Loggers.log("===================================");
        List<Integer> selections = new ArrayList<>();
        selections.add(Integer.valueOf("1"));
        selections.add(Integer.valueOf("1"));
        selections.add(Integer.valueOf("1"));
        selections.add(Integer.valueOf("1"));
        selections.add(Integer.valueOf("1"));
        selections.add(Integer.valueOf("2"));
        selections.add(Integer.valueOf("2"));
        selections.add(Integer.valueOf("2"));
        selections.add(Integer.valueOf("2"));
        selections.add(Integer.valueOf("2"));
        selections.add(Integer.valueOf("2"));

        Order order = orderService.createOrder(products, selections);
        orderService.processOrder(order);
        orderService.printInvoice(order);

        assertEquals(order.getAmountToPay(), Double.valueOf("25"));
    }

    @Test
    public void shouldProcessOrderWithOneSmallCoffeeWithExtraMilkAndOneBaconRoll() {
        Loggers.log("===================================");
        Loggers.log("Integration test: The client choose one small coffee with extra milk and one bacon roll, " +
                "then the extra is for free, the total amount should be 7 CHF");
        Loggers.log("===================================");
        List<Integer> selections = new ArrayList<>();
        selections.add(Integer.valueOf("1"));
        selections.add(Integer.valueOf("4"));
        selections.add(Integer.valueOf("6"));

        Order order = orderService.createOrder(products, selections);
        orderService.processOrder(order);
        orderService.printInvoice(order);

        assertEquals(order.getAmountToPay(), Double.valueOf("7"));
    }

    @Test
    public void shouldProcessOrderWithOneSmallCoffeeWithTwoExtraMilkAndOneBaconRoll() {
        Loggers.log("===================================");
        Loggers.log("Integration test: The client choose one small coffee with 2 extra milk and one bacon roll, " +
                "then one extra is for free, the total amount should be 7.3 CHF");
        Loggers.log("===================================");
        List<Integer> selections = new ArrayList<>();
        selections.add(Integer.valueOf("1"));
        selections.add(Integer.valueOf("4"));
        selections.add(Integer.valueOf("6"));
        selections.add(Integer.valueOf("6"));

        Order order = orderService.createOrder(products, selections);
        orderService.processOrder(order);
        orderService.printInvoice(order);

        assertEquals(order.getAmountToPay(), Double.valueOf("7.3"));
    }
}
