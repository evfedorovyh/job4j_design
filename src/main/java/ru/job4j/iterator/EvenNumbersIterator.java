package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return findEven(index) >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = findEven(index);
        return data[index++];
    }

    private int findEven(int start) {
        int result = -1;
        for (int i = start; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }
}
