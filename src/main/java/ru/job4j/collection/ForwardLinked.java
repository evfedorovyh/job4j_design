package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public ForwardLinked() {
    }

    public void add(T value) {
        Node<T> l = head;
        for (int i = 1; i < size; i++) {
            l = l.next;
        }
        final Node<T> newNode = new Node<>(value, null);
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> l = head;
        for (int i = 0; i < index; i++) {
            l = l.next;
        }
        return l.item;
    }

    public T deleteFirst() {
        T item;
        if (head != null && head.next != null) {
            item = head.item;
            head = head.next;
        } else if (head != null) {
            item = head.item;
            head = new Node<>(null, null);
        } else {
            throw new NoSuchElementException();
        }
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            Node<T> node;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return ((node == null && head != null)
                        || (node != null && node.next != null));
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (node == null) {
                    node = head;
                } else {
                    node = node.next;
                }
                return node.item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
