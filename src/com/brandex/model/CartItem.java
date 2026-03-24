package com.brandex.model;

public class CartItem {
    private Product product;
    private int quantity;
    private double subtotal;

    // Used for undo/redo encoding: positive = ADD action, negative = REMOVE action
    private int previousQuantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getPrice() * quantity;
        this.previousQuantity = 0;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getSubtotal() { return subtotal; }
    public int getPreviousQuantity() { return previousQuantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = product.getPrice() * quantity;
    }

    public void setPreviousQuantity(int previousQuantity) { this.previousQuantity = previousQuantity; }

    public void calculateSubtotal() { this.subtotal = product.getPrice() * quantity; }

    @Override
    public String toString() {
        return String.format("%s x%d = $%.2f", product.getName(), quantity, subtotal);
    }
}
