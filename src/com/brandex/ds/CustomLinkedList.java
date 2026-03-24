package com.brandex.ds;

import java.util.ArrayList;
import java.util.List;

public class CustomLinkedList<T> {
    private Node<T> head;
    private int size;

    public CustomLinkedList() { head = null; size = 0; }

    public boolean addItem(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) { head = newNode; }
        else {
            Node<T> cur = head;
            while (cur.getNext() != null) cur = cur.getNext();
            cur.setNext(newNode);
        }
        size++;
        return true;
    }

    public boolean remove(int index) {
        if (index < 0 || index >= size) return false;
        if (index == 0) { head = head.getNext(); size--; return true; }
        Node<T> cur = head;
        for (int i = 0; i < index - 1; i++) cur = cur.getNext();
        cur.setNext(cur.getNext().getNext());
        size--;
        return true;
    }

    public T getIndex(int index) {
        if (index < 0 || index >= size) return null;
        Node<T> cur = head;
        for (int i = 0; i < index; i++) cur = cur.getNext();
        return cur.getData();
    }

    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }

    public List<T> getItems() {
        List<T> list = new ArrayList<>();
        Node<T> cur = head;
        while (cur != null) { list.add(cur.getData()); cur = cur.getNext(); }
        return list;
    }

    public void display() {
        Node<T> cur = head;
        while (cur != null) { System.out.println(cur.getData()); cur = cur.getNext(); }
    }

    public void clear() { head = null; size = 0; }
}
