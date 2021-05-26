package com.coffeecorner;

import static org.junit.Assert.assertTrue;

import com.coffeecorner.common.Constants;
import com.coffeecorner.service.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple CornerCoffeeApplication.
 */
public class CornerCoffeeApplicationTest
{
    private FileResourcesService fileResourcesService;

    private ProductService productService;

    private SelectionService selectionService;

    private OrderService orderService;

    private CornerCoffeeApplication cornerCoffeeApplication;

    @Before
    public void setUp() {
        fileResourcesService = new FileResourcesServiceImpl();
        productService = new ProductServiceImpl(fileResourcesService);
        selectionService = new SelectionServiceImpl();
        orderService = new OrderServiceImpl();
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        cornerCoffeeApplication = new CornerCoffeeApplication(productService, orderService, selectionService);
        assertTrue( true );
    }
}
