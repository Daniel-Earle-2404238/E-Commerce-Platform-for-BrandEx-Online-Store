package com.brandex.ds;

import com.brandex.model.Product;

public class BSTNode {
    private Product product;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(Product product) { this.product = product; this.left = null; this.right = null; }

    public Product getProduct() { return product; }
    public BSTNode getLeft() { return left; }
    public BSTNode getRight() { return right; }
    public void setProduct(Product product) { this.product = product; }
    public void setLeft(BSTNode left) { this.left = left; }
    public void setRight(BSTNode right) { this.right = right; }
}
