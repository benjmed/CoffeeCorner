package com.coffeecorner.domain;

import java.util.Objects;

/**
 * @author Mohamed.Benjelloun
 * domain class for the product
 */
public class Product {

    /**
     * id:product identifier.
     */
    private final Integer id;

    /**
     * type:product type.
     */
    private final ProductType type;

    /**
     * name:product name.
     */
    private final String name;

    /**
     * size:product size.
     */
    private final String size;

    /**
     * price:product price.
     */
    private final Double price;

    /**
     * currency:product currency.
     */
    private final String currency;

    /**
     * @param id       identifier
     * @param type     product type
     * @param name     product name
     * @param size     size of the product
     * @param price    price of the product
     * @param currency currency of the product
     */
    public Product(Integer id, ProductType type, String name, String size, Double price, String currency) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.size = size;
        this.price = price;
        this.currency = currency;
    }

    /**
     * @return id product identifier
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return type product type
     */
    public ProductType getType() {
        return type;
    }

    /**
     * @return name product name
     */
    public String getName() {
        return name;
    }

    /**
     * @return size size of the product
     */
    public String getSize() {
        return size;
    }

    /**
     * @return price price of the product
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @return currency currency of the product
     */
    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId()) && getType() == product.getType() && getName().equals(product.getName())
                && Objects.equals(getSize(), product.getSize()) && getPrice().equals(product.getPrice())
                && getCurrency().equals(product.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getName(), getSize(), getPrice(), getCurrency());
    }

    @Override
    public String toString() {
        String result;
        if (this.size != null && !this.size.equals("")) {
            result = String.format("%s (%s) %s %s",
                    this.name,
                    this.size,
                    this.price,
                    this.currency);
        } else {
            result = String.format("%s %s %s",
                    this.name,
                    this.price,
                    this.currency);
        }
        return result;
    }
}
