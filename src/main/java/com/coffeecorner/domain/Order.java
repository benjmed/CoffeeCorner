package com.coffeecorner.domain;

import java.util.List;

/**
 * @author Mohamed.Benjelloun
 * domain class for the product
 */
public class Order {

    /**
     * products:list of products.
     */
    private final List<Product> products;

    /**
     * freeBeverages:list of free Beverages.
     */
    private List<Product> freeBeverages;

    /**
     * freeExtras:list of free Extras.
     */
    private List<Product> freeExtras;

    /**
     * totalAmount: total amount before discounts.
     */
    private Double totalAmount;

    /**
     * discounts: discounts to apply.
     */
    private Double discounts;

    /**
     * amountToPay: amount to pay.
     */
    private Double amountToPay;

    /**
     * Order constructor
     * @param products list of products
     */
    public Order(List<Product> products) {
        super();
        this.products = products;
    }

    /**
     * method that returns the list of products
     * @return products list of products
     */
    public List<Product> getProducts() {
        return this.products;
    }

    /**
     * method that returns the list of free beverages
     * @return freeBeverages:list of free Beverages.
     */
    public List<Product> getFreeBeverages() {
        return this.freeBeverages;
    }

    /**
     * set the freeBeverages
     * @param freeBeverages:list of free Beverages.
     */
    public void setFreeBeverages(List<Product> freeBeverages) {
        this.freeBeverages = freeBeverages;
    }

    /**
     * method that returns the list of free extras
     * @return freeExtras:list of free extras.
     */
    public List<Product> getFreeExtras() {
        return this.freeExtras;
    }

    /**
     * set the freeExtras
     * @param freeExtras:list of free extras.
     */
    public void setFreeExtras(List<Product> freeExtras) {
        this.freeExtras = freeExtras;
    }

    /**
     * method that returns the totalAmount.
     * @return total amount before discounts.
     */
    public Double getTotalAmount() {
        return this.totalAmount;
    }

    /**
     * set the total amount
     * @param totalAmount:total amount before discounts.
     */
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * method that returns the discounts.
     * @return discounts: discounts to apply
     */
    public Double getDiscounts() {
        return discounts;
    }

    /**
     * set the discounts
     * @param discounts: discounts to apply
     */
    public void setDiscounts(Double discounts) {
        this.discounts = discounts;
    }

    /**
     * method that returns the amountToPay.
     * @return amountToPay: amount to pay
     */
    public Double getAmountToPay() {
        return amountToPay;
    }

    /**
     * set the amountToPay
     * @param amountToPay: the amount to pay
     */
    public void setAmountToPay(Double amountToPay) {
        this.amountToPay = amountToPay;
    }
}
