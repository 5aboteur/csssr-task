package com.github.saboteur.csssr.datastructure.impl;

import java.util.*;

public class TrieNode {

    private final Character ch;
    private int count;
    private boolean isWord;
    private final SortedMap<Character, TrieNode> children;

    public TrieNode(Character ch) {
        this.ch = ch;
        this.count = 0;
        this.isWord = false;
        this.children = new TreeMap<>();
    }

    public Character getCh() {
        return ch;
    }

    public int getCount() {
        return count;
    }

    public void incCount() {
        count++;
    }

    public boolean isWord() {
        return isWord;
    }

    public void markAsWord() {
        isWord = true;
    }

    public SortedMap<Character, TrieNode> getChildren() {
        return children;
    }

}
