package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    public SimpleLinkedList() {
    }

    @Override
    public void add(E value) {
        Node<E> l = head;
        for (int i = 1; i < size; i++) {
            l = l.next;
        }
        final Node<E> newNode = new Node<>(value, null);
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> l = head;
        for (int i = 0; i < index; i++) {
            l = l.next;
        }
        return l.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final int expectedModCount = modCount;
            Node<E> node;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return ((node == null && head != null)
                        || (node != null && node.next != null));
            }

            @Override
            public E next() {
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

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
