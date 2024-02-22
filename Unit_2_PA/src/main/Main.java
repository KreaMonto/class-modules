package main;

import com.ecommerce.*;
import com.ecommerce.orders.*;

public class Main {

	public static void main(String[] args) {
		
		// Creating some products to use		
		Product laptop = new Product(1, "Laptop", 1200.0, "just a laptop");
		Product backpack = new Product(1, "Backpack", 120.0, "just a backpack");
		
		
		// now creating the customer
		Customer customer1 = new Customer(101, "Daniel");
		
		
		//the customer adds items to the cart
		customer1.addToCart(backpack, 2);
		customer1.addToCart(laptop, 1);
		
		
		// now to show some product info... 
        System.out.println("Product Information:");
        System.out.println("1. " + laptop.getName() + " - $" + laptop.getPrice());
        System.out.println("2. " + backpack.getName() + " - $" + backpack.getPrice());
        System.out.println();
        
        
        // display customer info
        System.out.println("Customer Information:");
        System.out.println("Customer 1: " + customer1.getName() + " (ID: " + customer1.getCustomerID() + ")");
        System.out.println();
		
        
        // showing the order info
        System.out.println("============================");
        System.out.println("Order Information:");
        System.out.println("----------------------------");
        // now here the order will be placed by the customer or customers...
        customer1.placeOrder();
        System.out.println("============================");
	}

}
