package com.ecommerce;

public class Product {
	private int productID;
	private String name;
	private double price;
	private String description;
	
	//Constructor ------------------
	public Product(int productID, String name, double price, String description) {
		this.productID = productID;
		this.name = name;
		this.price  = price;
		this.description = description;	
	}
	
	// getters and the setters ------
	// productID --------------------------------------------------------------------
	public int getProductID() {
		return productID;
	}
	
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	// name -------------------------------------------------------------------------
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// price ------------------------------------------------------------------------
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	// description ------------------------------------------------------------------
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	// end of setters and getters ----

	
}
