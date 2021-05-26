package com.coffeecorner;

import com.coffeecorner.common.Constants;
import com.coffeecorner.domain.Order;
import com.coffeecorner.domain.Product;
import com.coffeecorner.service.*;

import java.util.List;
import java.util.Map;

/**
 * @author Mohamed.Benjelloun
 * CornerCoffeeApplication main class
 */
public class CornerCoffeeApplication
{

    /**
     * productService:the product service.
     */
    private final ProductService productService;

    /**
     * orderService:the order service.
     */
    private final OrderService orderService;

    /**
     * selectionService:the selection service.
     */
    private final SelectionService selectionService;

    /**
     *
     * @param productService the product service.
     * @param orderService the order service.
     * @param selectionService the selection service.
     */
    public CornerCoffeeApplication(ProductService productService, OrderService orderService, SelectionService selectionService) {
        this.productService = productService;
        this.orderService = orderService;
        this.selectionService = selectionService;
    }

    public static void main(String[] args )
    {
        //to initialize and construct the services
        FileResourcesService fileResourcesService = new FileResourcesServiceImpl();
        ProductService productService = new ProductServiceImpl(fileResourcesService);
        OrderService orderService = new OrderServiceImpl();
        SelectionService selectionService = new SelectionServiceImpl();
        CornerCoffeeApplication cornerCoffeeApplication = new CornerCoffeeApplication(productService, orderService, selectionService);

        //List which  store the options chosen by the customer
        List<Integer> selections;
        //order that will contain the products chosen by the customer
        Order order;
        //map to present the products to the customer
        Map<Integer, Product> products = cornerCoffeeApplication.getProductService().getProducts(Constants.PRODUCTS_FILE);

        //Present the menu to the customer
        cornerCoffeeApplication.getSelectionService().printMenu(products);

        selections = cornerCoffeeApplication.getSelectionService().readSelections(products.keySet());

        //make an order
        order = cornerCoffeeApplication.getOrderService().createOrder(products, selections);

        if (order != null) {
            // Process the order
            cornerCoffeeApplication.getOrderService().processOrder(order);

            // Print invoice
            cornerCoffeeApplication.getOrderService().printInvoice(order);
        } else {
            System.out.println("\nNo order han been attended for your selections");
        }

    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public SelectionService getSelectionService() {
        return selectionService;
    }
}
