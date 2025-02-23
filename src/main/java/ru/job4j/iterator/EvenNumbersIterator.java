package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index;
    private int indexIs;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        indexIs = index;
        while (indexIs < data.length) {
            if ((data[indexIs] % 2) == 0) {
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
