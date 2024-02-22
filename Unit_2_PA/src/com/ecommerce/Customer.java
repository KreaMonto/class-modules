package com.ecommerce;

import com.ecommerce.orders.Order;


public class Customer {
    private int customerID;
    private String name;
    private ShoppingCart shoppingCart;
    

 public Customer(int id, String name) {
	 this.customerID = id;
	 this.name = name;
	 this.shoppingCart = new ShoppingCart();
	}

	// setters and getters ---- 
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    // Methods for shopping cart operations
    public void addToCart(Product product, int quantity) {
        shoppingCart.addItem(product, quantity);
    }

    public void removeFromCart(Product product, int quantity) {
        shoppingCart.removeItem(product, quantity);
    }

    public double calculateTotalCost() {
        return ShoppingCart.calculateTotalCost();
    }

    public void placeOrder() {
        if (shoppingCart.getCartItems().isEmpty()) {
            System.out.println("Cannot place an empty order. Add products to the cart first.");
            return;
        }

        // Create an instance of the Order class
        Order newOrder = new Order(this, shoppingCart.getCartItems());

        // Additional logic for placing an order (you can customize this part)
        // For example, update inventory, generate order confirmation, etc.

        // Update the order status
        newOrder.updateOrderStatus("Processing");

        // Print order summary
        System.out.println("Order placed successfully!\n\n" + newOrder.generateOrderSummary());

        // Clear the shopping cart after placing the order
        shoppingCart.clearCart();
    }
}
