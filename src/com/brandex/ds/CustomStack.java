package com.brandex.ds;

public class CustomStack<T> {
    private StackNode<T> top;
    private int size;

    public CustomStack() { top = null; size = 0; }

    public boolean push(T item) {
        StackNode<T> n = new StackNode<>(item);
        n.setNext(top);
        top = n;
        size++;
        return true;
    }

    public T pop() {
        if (isEmpty()) return null;
        T data = top.getData();
        top = top.getNext();
        size--;
        return data;
    }

    public T peek() { return isEmpty() ? null : top.getData(); }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return false; }
    public int getSize() { return size; }

    public void display() {
        StackNode<T> cur = top;
        while (cur != null) { System.out.println(cur.getData()); cur = cur.getNext(); }
    }
}
