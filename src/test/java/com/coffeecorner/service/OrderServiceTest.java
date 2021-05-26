package com.coffeecorner.service;

import com.coffeecorner.common.Constants;
import com.coffeecorner.domain.Order;
import com.coffeecorner.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderServiceTest {

    private FileResourcesService fileResourcesService;

    private ProductService productService;

    private OrderService orderService;

    private Map<Integer, Product> products;

    @Before
    public void setUp() {
        fileResourcesService = new FileResourcesServiceImpl();
        productService = new ProductServiceImpl(fileResourcesService);
        orderService = new OrderServiceImpl();
        products = productService.getProducts(Constants.PRODUCTS_FILE);
    }

    @Test
    public void shouldCreateOrderWithSmallCoffee()
    {
        List<Integer> selections = new ArrayList<>();
        selections.add(Integer.valueOf("1"));
        Order order = orderService.createOrder(products, selections);
        assertNotNull(order);
        assertEquals(order.getProducts().get(0).getName(), "Coffee");
        assertEquals(order.getProducts().get(0).getSize(), "small");
    }

    @Test
    public void shouldProcessOrderWithFreeExtra()
    {
        List<Integer> selections = new ArrayList<>();
        selections.add(Integer.valueOf("2"));
        selections.add(Integer.valueOf("4"));
        selections.add(Integer.valueOf("8"));
        Order order = orderService.createOrder(products, selections);
        orderService.processOrder(order);
        assertEquals(order.getDiscounts(), Double.valueOf("0.9"));
        assertEquals(order.getAmountToPay(), Double.valueOf("7.5"));

    }
}
