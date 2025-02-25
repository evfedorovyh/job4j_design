package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int size;

    public SimpleQueue() {
    }

    public T poll() {
        T pollValue;
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        for (int i = 1; i < size; i++) {
            output.push(input.pop());
        }
        pollValue = input.pop();
        size--;
        for (int i = 0; i < size; i++) {
            input.push(output.pop());
        }
        return pollValue;
    }

    public void push(T value) {
        input.push(value);
        size++;
    }
}
