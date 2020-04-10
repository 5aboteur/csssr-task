package com.github.saboteur.csssr.datastructure.impl;

import com.github.saboteur.csssr.comparators.LengthThenLexicographicalComparator;
import com.github.saboteur.csssr.datastructure.DataStructure;

import java.util.*;
import java.util.stream.Collectors;

public class SimplePrefixMap implements DataStructure {

    private final SortedMap<Character, SortedSet<String>> map;
    private long totalNumberOfStoredItems;

    public SimplePrefixMap() {
        map = new TreeMap<>();
        totalNumberOfStoredItems = 0;
    }

    public SortedMap<Character, SortedSet<String>> get() {
        return map;
    }

    public long getTotalNumberOfStoredItems() {
        return totalNumberOfStoredItems;
    }

    @Override
    public void insert(String item) {
        if (item == null)
            throw new IllegalArgumentException("Null key provided");

        if (item.isBlank() || item.isEmpty())
            return;

        var key = item.charAt(0);

        if (map.containsKey(key)) {
            var storedItems = map.get(key);
            storedItems.add(item);
        } else {
            var set = new TreeSet<>(LengthThenLexicographicalComparator.INSTANCE);
            set.add(item);
            map.put(key, set);
        }

        totalNumberOfStoredItems++;
    }

    @Override
    public String toString() {
        return map
            .entrySet()
            .stream()
            .filter(e -> e.getValue().size() > 1)
            .map(e -> e.getKey() + "=" + e.getValue())
            .collect(Collectors.joining(", ", "[", "]"));
    }

}
