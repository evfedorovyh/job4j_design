package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (LOAD_FACTOR < (float) (count + 1) / capacity) {
            expand();
        }
        int index = indexFor(key);
        boolean result = (table[index] == null);
        if (result) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return result;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexFor(key);
        if (equalsKey(index, key)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(key);
        boolean result = equalsKey(index, key);
        if (result) {
            table[index] = null;
            count--;
            modCount++;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hashCode(K key) {
        return Objects.hashCode(key);
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int indexFor(K key) {
        return indexFor(hash(hashCode(key)));
    }

    private boolean equalsKey(int index, K key) {
        MapEntry<K, V> entry = table[index];
        return  (entry != null
                && hashCode(entry.key) == hashCode(key)
                && Objects.equals(entry.key, key));
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                newTable[indexFor(entry.key)] = entry;
            }
        }
        table = newTable;
        modCount++;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, Integer> map = new NonCollisionMap<>();
        int[] hashCode = {0, 65535, 65536};
        for (int h : hashCode) {
            System.out.printf("Если hashCode = " + h + ", то hash равен: %d%n", map.hash(h));
        }
        System.out.println();
        int[] hash = {0, 7, 8};
        for (int h : hash) {
            System.out.printf("Если hash = " + h + ", то index равен: %d%n", map.indexFor(h));
        }

        System.out.println();
        int[] key = {0, 1, 7, 8, 15};
        for (int h : key) {
            System.out.printf("Если key = " + h + ", то hashCode равен: %d%n", map.hashCode(h));
        }

        System.out.println();
        int[] key1 = {0, 1, 7, 8, 15};
        for (int h : key1) {
            System.out.printf("Если key = " + h + ", то index равен: %d%n", map.indexFor(h));
        }
    }
}

