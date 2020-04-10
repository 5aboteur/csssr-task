package com.github.saboteur.csssr;

import com.github.saboteur.csssr.datastructure.DataStructure;
import com.github.saboteur.csssr.datastructure.impl.SimplePrefixMap;
import com.github.saboteur.csssr.datastructure.impl.Trie;

public class CsssrApp {

    public static void main(String[] args) {
        String inputString = "сапог сарай арбуз болт бокс биржа";
        testSimplePrefixMap(inputString);
        testTrie(inputString);
    }

    private static void testSimplePrefixMap(String inputString) {
        try {
            DataStructure simplePrefixMap = new SimplePrefixMap();
            simplePrefixMap.parse(inputString);
            System.out.println("SimplePrefixMap: " + simplePrefixMap);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    private static void testTrie(String inputString) {
        try {
            DataStructure trie = new Trie();
            trie.parse(inputString);
            System.out.println("Trie: " + trie);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

}
