package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {
    private Integer[] data;
    private int index;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return findNotNull(index) >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = findNotNull(index);
        return data[index++];
    }

    private int findNotNull(int start) {
        int result = -1;
        for (int i = start; i < data.length; i++) {
            if (data[i] != null) {
                result = i;
                break;
            }
        }
        return result;
    }
}
