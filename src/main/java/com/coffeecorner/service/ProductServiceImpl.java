package com.coffeecorner.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import com.coffeecorner.domain.Product;
import com.coffeecorner.domain.ProductType;
import com.coffeecorner.common.Constants;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author Mohamed.Benjelloun
 * Implementation of the service ProductService
 */
public class ProductServiceImpl implements ProductService {

    private final FileResourcesService fileResourcesService;

    public ProductServiceImpl(FileResourcesService fileResourcesService) {
        this.fileResourcesService = new FileResourcesServiceImpl();
    }

    public Map<Integer, Product> getProducts(String fileName) {
        Map<Integer, Product> offering = new HashMap<>();
        String contentFile = getFileResourcesService().readFile("products.json");

        ScriptEngineManager sem = new ScriptEngineManager();
        try
        {
        ScriptEngine engine = sem.getEngineByName(Constants.ENGINE_NAME);

        Map<String, Object> map = (Map<String, Object>) engine.eval(Constants.SCRIPT.replace(Constants.DASH, contentFile));
        AtomicReference<List> products = new AtomicReference<>((List) map.get(Constants.PRODUCTS));

        products.get().forEach((p) -> {
            Map<String, Object> productAttrs = (Map<String, Object>) p;
            Product product = new Product(
                    (Integer) productAttrs.get(Constants.ID),
                    ProductType.valueOf(((String) productAttrs.get(Constants.TYPE)).toUpperCase()),
                    (String) productAttrs.get(Constants.NAME),
                    (String) productAttrs.get(Constants.SIZE),
                    (Double) productAttrs.get(Constants.PRICE),
                    (String) productAttrs.get(Constants.CURRENCY));
            offering.put((Integer) productAttrs.get(Constants.ID), product);
        });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offering;
    }

    private FileResourcesService getFileResourcesService() {
        return fileResourcesService;
    }
}
