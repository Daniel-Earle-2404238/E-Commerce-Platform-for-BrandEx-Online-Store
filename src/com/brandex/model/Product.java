package com.brandex.model;

public class Product {
    private int productID;
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product(int productID, String name, double price, int quantity, String category) {
        this.productID = productID; this.name = name; this.price = price;
        this.quantity = quantity; this.category = category;
    }

    public int getProductID() { return productID; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getCategory() { return category; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setCategory(String category) { this.category = category; }

    public String getProductID_str() { return String.valueOf(productID); }
    public String getName_str() { return name; }
    public double getPrice_double() { return price; }
    public int getQuantity_int() { return quantity; }
    public String getCategory_str() { return category; }

    public void displayProduct() { System.out.println(this); }

    @Override
    public String toString() {
        return String.format("[%d] %s | $%.2f | Qty: %d | %s", productID, name, price, quantity, category);
    }
}
