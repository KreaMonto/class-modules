package com.ecommerce.orders;

import java.util.HashMap;
import java.util.Map;

//importing the necessary classes from the com.ecommerce module
import com.ecommerce.Customer;
import com.ecommerce.Product;
import com.ecommerce.ShoppingCart;

public class Order {
	// we will need to set an ID for each order that will be managed later in a separated method
    private static int nextOrderID = 1;

    // setting the attributes
    private int orderID;
    private Customer customer;
    private Map<Product, Integer> products;
    private double orderTotal;
    private String orderStatus;

    // Constructors
	public Order(Customer customer, Map<Product, Integer> cartItems) {
		double totalOrder = ShoppingCart.calculateTotalCost();
		
        this.orderID = getNextOrderID();
        this.customer = customer;
        this.products = new HashMap<>(cartItems);
        this.orderTotal = totalOrder;
        this.orderStatus = "Pending"; // Default status
    }

    // we will need to set some getters
    // getter for orderID
    public int getOrderID() {
        return orderID;
    }

    // getter for customer
    public Customer getCustomer() {
        return customer;
    }

    // getter for products
    public Map<Product, Integer> getProducts() {
        return products;
    }

    // getter for orderTotal
    public double getOrderTotal() {
        return orderTotal;
    }

    // getter and Setter for orderStatus
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    
    // this method will generate the string to show all the order information
    // this was horrible to create, thank you...
    public String generateOrderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Order ID: ").append(orderID).append("\n");
        summary.append("Customer: ").append(customer.getName()).append("\n");
        summary.append("Customer ID: ").append(customer.getCustomerID()).append("\n");
        summary.append("\n");
        summary.append("Products:\n");        
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
        	Product product = entry.getKey();
        	int quantity = entry.getValue();
            summary.append("- ").append(product.getName())
                   .append(" ").append(quantity)
                   .append(" x $").append(product.getPrice())
                   .append("\n");
        }
        summary.append("Order Total: $").append(orderTotal).append("\n");
        summary.append("Order Status: ").append(orderStatus);
        return summary.toString();
    }

    // method to update the status
    public void updateOrderStatus(String newStatus) {
        this.orderStatus = newStatus;
    }

    // this method is to generate the next available order ID to the list
    private static synchronized int getNextOrderID() {
        return nextOrderID++;
    }
    
}
