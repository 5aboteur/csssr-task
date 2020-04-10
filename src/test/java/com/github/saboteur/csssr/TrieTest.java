package com.github.saboteur.csssr;

import com.github.saboteur.csssr.datastructure.impl.Trie;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TrieTest {

    Trie trie;

    @BeforeEach
    void setUp() {
        trie = new Trie();
    }

    @ParameterizedTest(name = "{0} => {1}")
    @CsvSource({
        "'', 0",
        "'     ', 0",
        "' nu ka sobaka protestiruj ka menya kak sleduet!!! ', 8",
        "'sapog saraj arbuz bolt boks birzha', 6",
        "'123 123 123 123 ', 4"
    })
    void checkTotalNumberOfStoredItems(String inputString, int numberOfWords) {
        trie.parse(inputString);

        Assertions.assertEquals(numberOfWords, trie.getTotalNumberOfStoredItems());
    }

    @Test
    void checkOutputString() {
        String inputString = "eee ee e dd d a b bb c cc ccc";

        trie.parse(inputString);

        Assertions.assertEquals(
            "[b=[bb, b], c=[ccc, cc, c], d=[dd, d], e=[eee, ee, e]]",
            trie.toString()
        );
    }

    @Test
    void nullOrEmptyInputStringTest() {
        try {
            trie.parse(null);
        } catch (Exception ignored) { };

        Assertions.assertNotNull(trie.get());
        Assertions.assertEquals(0, trie.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", trie.toString());

        trie.parse("");

        Assertions.assertNotNull(trie.get());
        Assertions.assertEquals(0, trie.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", trie.toString());

        trie.parse("\t");

        Assertions.assertNotNull(trie.get());
        Assertions.assertEquals(0, trie.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", trie.toString());

        trie.parse("   \n    \r   \n   ");

        Assertions.assertNotNull(trie.get());
        Assertions.assertEquals(0, trie.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", trie.toString());

        try {
            trie.insert(null);
        } catch (Exception ignored) { };

        Assertions.assertNotNull(trie.get());
        Assertions.assertEquals(0, trie.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", trie.toString());

        trie.insert("");

        Assertions.assertNotNull(trie.get());
        Assertions.assertEquals(0, trie.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", trie.toString());

        trie.insert("\t");

        Assertions.assertNotNull(trie.get());
        Assertions.assertEquals(0, trie.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", trie.toString());

        trie.insert("   \n    \r   \n   ");

        Assertions.assertNotNull(trie.get());
        Assertions.assertEquals(0, trie.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", trie.toString());
    }

    @Test
    void simpleTest() {
        String inputString = "nu ka sobaka protestiruj ka menya kak sleduet!!!";

        trie.parse(inputString);

        Assertions.assertEquals(8, trie.getTotalNumberOfStoredItems());

        var root = trie.get();

        Assertions.assertNotNull(root);
        Assertions.assertEquals(5, root.getChildren().values().size());
        Assertions.assertEquals(1, root.getChildren().get('n').getCount());
        Assertions.assertEquals(1, root.getChildren().get('p').getCount());
        Assertions.assertEquals(1, root.getChildren().get('m').getCount());
        Assertions.assertEquals(2, root.getChildren().get('s').getCount());
        Assertions.assertEquals(3, root.getChildren().get('k').getCount());
        Assertions.assertEquals(
            "[k=[kak, ka], s=[sleduet!!!, sobaka]]",
            trie.toString()
        );

        String[] anotherInputString = {
            "na",
            "na",
            "escho",
            "razok",
            "molorik"
        };

        for (var word : anotherInputString) {
            trie.insert(word);
        }

        Assertions.assertEquals(7, root.getChildren().values().size());
        Assertions.assertEquals(3, root.getChildren().get('n').getCount());
        Assertions.assertEquals(1, root.getChildren().get('e').getCount());
        Assertions.assertEquals(1, root.getChildren().get('r').getCount());
        Assertions.assertEquals(2, root.getChildren().get('m').getCount());
        Assertions.assertEquals(
            "[k=[kak, ka], m=[molorik, menya], n=[na, nu], s=[sleduet!!!, sobaka]]",
            trie.toString()
        );
    }

}
