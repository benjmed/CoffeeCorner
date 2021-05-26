package com.coffeecorner.service;

import com.coffeecorner.common.Constants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class FileResourcesServiceImplTest {


    private FileResourcesService fileResourcesService;

    @Before
    public void setUp() {
        fileResourcesService = new FileResourcesServiceImpl();
    }

    @Test
    public void shouldOpenProductsFile()
    {
        String content = fileResourcesService.readFile(Constants.PRODUCTS_FILE);
        assertNotNull(content);
    }
}
