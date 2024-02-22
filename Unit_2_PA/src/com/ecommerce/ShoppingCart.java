package com.ecommerce;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	
	// attribute ----
	private static Map<Product, Integer> cartItems;
	
	// constructor --------------
	
	public ShoppingCart() {
		ShoppingCart.cartItems = new HashMap<>();
	}
	
	
	// now, need to add methods for the cart operations as, putting and removing items
	// and then calculate the total cost...
	
	public void addItem(Product product, int quantity) {
		// the method puts an item in the Map cartItems ensuring the item does not exist
		// and then updating the quantity
		cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
	}
	
	public void removeItem(Product product, int quantity) {
		// first we get the quantity of the product
		int currentQuantity = cartItems.getOrDefault(product, 0);
		
		// then we ensure if we are taking all of the product or just a few
		if (currentQuantity > quantity) {
			cartItems.put(product, currentQuantity - quantity); // Subtracting the quantity we need to remove
		} else {
			cartItems.remove(product); // or totally remove... 
		}
	}
	
    public static double calculateTotalCost() {
    	// we create the variable that will be returned with the total
        double totalCost = 0.0;
        // now we do a map called entry to iterate in it 
        try {
			for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
				// now we get the price of each product with entry.getKey().getPrice() multiplied by the quantity
				//and sum to total cost
			    totalCost += entry.getKey().getPrice() * entry.getValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return totalCost;
    }

    
    public Map<Product, Integer> getCartItems() {
        return new HashMap<>(cartItems);
    }


	public void clearCart() {
		
		
	}
}
