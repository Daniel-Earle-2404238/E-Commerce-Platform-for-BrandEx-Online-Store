package com.brandex.ds;

import com.brandex.model.Product;
import java.util.ArrayList;
import java.util.List;

public class BST {
    private BSTNode root;

    public BST() { root = null; }

    public void insert(Product p) { root = insertRec(root, p); }

    private BSTNode insertRec(BSTNode node, Product p) {
        if (node == null) return new BSTNode(p);
        int cmp = p.getName().compareToIgnoreCase(node.getProduct().getName());
        if (cmp < 0) node.setLeft(insertRec(node.getLeft(), p));
        else if (cmp > 0) node.setRight(insertRec(node.getRight(), p));
        return node;
    }

    public void delete(int productID) { root = deleteRec(root, productID); }

    private BSTNode deleteRec(BSTNode node, int id) {
        if (node == null) return null;
        if (id == node.getProduct().getProductID()) {
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();
            BSTNode min = findMin(node.getRight());
            node.setProduct(min.getProduct());
            node.setRight(deleteRec(node.getRight(), min.getProduct().getProductID()));
        } else {
            node.setLeft(deleteRec(node.getLeft(), id));
            node.setRight(deleteRec(node.getRight(), id));
        }
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }

    public Product searchByName(String name) { return searchByNameRec(root, name); }

    private Product searchByNameRec(BSTNode node, String name) {
        if (node == null) return null;
        int cmp = name.compareToIgnoreCase(node.getProduct().getName());
        if (cmp == 0) return node.getProduct();
        if (cmp < 0) return searchByNameRec(node.getLeft(), name);
        return searchByNameRec(node.getRight(), name);
    }

    public Product searchByID(int id) { return searchByIDRec(root, id); }

    private Product searchByIDRec(BSTNode node, int id) {
        if (node == null) return null;
        if (id == node.getProduct().getProductID()) return node.getProduct();
        Product l = searchByIDRec(node.getLeft(), id);
        return l != null ? l : searchByIDRec(node.getRight(), id);
    }

    public List<Product> searchPartial(String query) {
        List<Product> results = new ArrayList<>();
        searchPartialRec(root, query.toLowerCase(), results);
        return results;
    }

    private void searchPartialRec(BSTNode node, String query, List<Product> results) {
        if (node == null) return;
        if (node.getProduct().getName().toLowerCase().contains(query))
            results.add(node.getProduct());
        searchPartialRec(node.getLeft(), query, results);
        searchPartialRec(node.getRight(), query, results);
    }

    public List<Product> inOrder() {
        List<Product> list = new ArrayList<>();
        inOrderRec(root, list);
        return list;
    }

    private void inOrderRec(BSTNode node, List<Product> list) {
        if (node == null) return;
        inOrderRec(node.getLeft(), list);
        list.add(node.getProduct());
        inOrderRec(node.getRight(), list);
    }
}
