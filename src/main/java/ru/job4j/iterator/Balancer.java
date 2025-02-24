package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int countOfNodes = nodes.size();
        int index = 0;
        if (countOfNodes != 0) {
            while (source.hasNext()) {
                index %= countOfNodes;
                nodes.get(index++).add(source.next());
            }
        }
    }
}
