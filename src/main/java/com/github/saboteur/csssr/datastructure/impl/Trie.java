package com.github.saboteur.csssr.datastructure.impl;

import com.github.saboteur.csssr.comparators.LengthThenLexicographicalComparator;
import com.github.saboteur.csssr.datastructure.DataStructure;

import java.util.*;

public class Trie implements DataStructure {

    private final TrieNode root;
    private long totalNumberOfStoredItems;

    public Trie() {
        char rootCharacter = '\0';
        root = new TrieNode(rootCharacter);
        totalNumberOfStoredItems = 0;
    }

    public TrieNode get() {
        return root;
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

        TrieNode currentNode = root;

        for (int i = 0; i < item.length(); i++) {
            Character nextChar = item.charAt(i);
            TrieNode nextNode = currentNode.getChildren().get(nextChar);

            if (nextNode == null) {
                nextNode = new TrieNode(nextChar);
                currentNode.getChildren().put(nextChar, nextNode);
            }

            currentNode = currentNode.getChildren().get(nextChar);

            if (i == 0)
                currentNode.incCount();
        }

        currentNode.markAsWord();

        totalNumberOfStoredItems++;
    }

    private String traversalOutput() {
        var result = new StringBuilder().append("[");
        var item = new StringBuilder();
        var setOfItems = new TreeSet<>(LengthThenLexicographicalComparator.INSTANCE);
        var removeRedundant = false;

        for (TrieNode child : root.getChildren().values()) {
            if (child.getCount() > 1) {
                result.append(child.getCh()).append("=[");
                item.append(child.getCh());
                traverse(child, setOfItems, item);
                result.append(String.join(", ", setOfItems)).append("], ");
                removeRedundant = true;

                item.delete(0, item.length());
                setOfItems.clear();
            }
        }

        if (removeRedundant) {
            var len = result.length();
            result.delete(len - 2, len);
        }

        return result.append("]").toString();
    }

    private void traverse(TrieNode currentNode, Set<String> setOfItems, StringBuilder item) {
        if (currentNode == null || setOfItems == null || item == null)
            return;

        if (currentNode.getChildren().size() > 0) {
            for (var child : currentNode.getChildren().values()) {
                var itemLen = item.length();
                item.append(child.getCh());
                traverse(child, setOfItems, item);
                item.deleteCharAt(itemLen);
            }
        }

        if (currentNode.isWord()) {
            setOfItems.add(item.toString());
        }
    }

    @Override
    public String toString() {
        return traversalOutput();
    }

}
