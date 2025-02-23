package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {
    private Integer[] data;
    private int index;
    private int indexIs;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        indexIs = index;
        while (indexIs < data.length) {
            if ((data[indexIs] != null)) {
                return true;
            }
            indexIs++;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = indexIs;
        return data[index++];
    }
}
