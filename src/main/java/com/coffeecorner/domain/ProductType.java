package com.coffeecorner.domain;

/**
 * @author Mohamed.Benjelloun
 * types of the product
 */
public enum ProductType {

    BEVERAGE("beverage"),
    EXTRA("extra"),
    SNACK("snack");

    /**
     * type: type of the product.
     */
    final String type;

    /**
     * constructor of ProductType
     * @param type: the type of the product.
     */
    ProductType(String type) {
        this.type = type;
    }
}
