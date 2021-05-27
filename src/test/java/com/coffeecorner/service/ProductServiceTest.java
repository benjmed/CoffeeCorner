package com.coffeecorner.service;

import com.coffeecorner.common.Constants;
import com.coffeecorner.domain.Product;
import com.coffeecorner.service.impl.FileResourcesServiceImpl;
import com.coffeecorner.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class ProductServiceTest {

    private FileResourcesService fileResourcesService;

    private ProductService productService;

    @Before
    public void setUp() {
        fileResourcesService = new FileResourcesServiceImpl();
        productService = new ProductServiceImpl(fileResourcesService);
    }

    @Test
    public void shouldGetProducts()
    {
        Map<Integer, Product> offering = productService.getProducts(Constants.PRODUCTS_FILE);
        assertNotNull(offering.get(1));
    }
}
