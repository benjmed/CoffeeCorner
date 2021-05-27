package com.coffeecorner.service;

import com.coffeecorner.common.Constants;
import com.coffeecorner.domain.Product;
import com.coffeecorner.service.impl.FileResourcesServiceImpl;
import com.coffeecorner.service.impl.ProductServiceImpl;
import com.coffeecorner.service.impl.SelectionServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class SelectionServiceTest {

    private FileResourcesService fileResourcesService;

    private ProductService productService;

    private SelectionService selectionService;

    private Map<Integer, Product> products;

    @Before
    public void setUp() {
        fileResourcesService = new FileResourcesServiceImpl();
        productService = new ProductServiceImpl(fileResourcesService);
        selectionService = new SelectionServiceImpl();
        products = productService.getProducts(Constants.PRODUCTS_FILE);
    }

    @Test
    public void shouldPrintMenu()
    {
        selectionService.printMenu(products);
        assertTrue( true );
    }
}
