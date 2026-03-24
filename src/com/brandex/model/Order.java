package com.brandex.model;

import com.brandex.ds.CustomLinkedList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private int orderID;
    private int customerID;
    private CustomLinkedList<CartItem> items;
    private String orderDate;
    private String status;
    private double totalPrice;

    public Order(int orderID, int customerID, CustomLinkedList<CartItem> items) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.items = items;
        this.orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.status = "PENDING";
        this.totalPrice = calculateTotal();
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : items.getItems()) total += item.getSubtotal();
        return total;
    }

    public int getOrderID() { return orderID; }
    public int getCustomerID() { return customerID; }
    public CustomLinkedList<CartItem> getItems() { return items; }
    public String getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
    public double getTotalPrice() { return totalPrice; }

    public void setStatus(String status) { this.status = status; }
    public void setOrderDate(String date) { this.orderDate = date; }
    public void setTotalPrice(double price) { this.totalPrice = price; }

    public void displayOrder() { System.out.println(this); }

    @Override
    public String toString() {
        return String.format("Order #%d | %s | Status: %s | Total: $%.2f", orderID, orderDate, status, totalPrice);
    }
}
