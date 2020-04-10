package com.github.saboteur.csssr;

import com.github.saboteur.csssr.datastructure.impl.SimplePrefixMap;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

public class SimplePrefixMapTest {

    SimplePrefixMap simplePrefixMap;

    @BeforeEach
    void setUp() {
        simplePrefixMap = new SimplePrefixMap();
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
        simplePrefixMap.parse(inputString);

        Assertions.assertEquals(numberOfWords, simplePrefixMap.getTotalNumberOfStoredItems());
    }

    @Test
    void checkOutputString() {
        String inputString = "eee ee e dd d a b bb c cc ccc";

        simplePrefixMap.parse(inputString);

        Assertions.assertEquals(
            "[b=[bb, b], c=[ccc, cc, c], d=[dd, d], e=[eee, ee, e]]",
            simplePrefixMap.toString()
        );
    }

    @Test
    void nullOrEmptyInputStringTest() {
        try {
            simplePrefixMap.parse(null);
        } catch (Exception ignored) { };

        Assertions.assertNotNull(simplePrefixMap.get());
        Assertions.assertEquals(0, simplePrefixMap.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", simplePrefixMap.toString());

        simplePrefixMap.parse("");

        Assertions.assertNotNull(simplePrefixMap.get());
        Assertions.assertEquals(0, simplePrefixMap.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", simplePrefixMap.toString());

        simplePrefixMap.parse("\t");

        Assertions.assertNotNull(simplePrefixMap.get());
        Assertions.assertEquals(0, simplePrefixMap.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", simplePrefixMap.toString());

        simplePrefixMap.parse("   \n    \r   \n   ");

        Assertions.assertNotNull(simplePrefixMap.get());
        Assertions.assertEquals(0, simplePrefixMap.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", simplePrefixMap.toString());

        try {
            simplePrefixMap.insert(null);
        } catch (Exception ignored) { };

        Assertions.assertNotNull(simplePrefixMap.get());
        Assertions.assertEquals(0, simplePrefixMap.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", simplePrefixMap.toString());

        simplePrefixMap.insert("");

        Assertions.assertNotNull(simplePrefixMap.get());
        Assertions.assertEquals(0, simplePrefixMap.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", simplePrefixMap.toString());

        simplePrefixMap.insert("\t");

        Assertions.assertNotNull(simplePrefixMap.get());
        Assertions.assertEquals(0, simplePrefixMap.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", simplePrefixMap.toString());

        simplePrefixMap.insert("   \n    \r   \n   ");

        Assertions.assertNotNull(simplePrefixMap.get());
        Assertions.assertEquals(0, simplePrefixMap.getTotalNumberOfStoredItems());
        Assertions.assertEquals("[]", simplePrefixMap.toString());
    }

    @Test
    void simpleTest() {
        String inputString = "nu ka sobaka protestiruj ka menya kak sleduet!!!";

        simplePrefixMap.parse(inputString);

        Assertions.assertEquals(8, simplePrefixMap.getTotalNumberOfStoredItems());

        var map = simplePrefixMap.get();

        Assertions.assertNotNull(map);
        Assertions.assertEquals(5, map.size());
        Assertions.assertEquals(Set.of("nu"), map.get('n'));
        Assertions.assertEquals(Set.of("protestiruj"), map.get('p'));
        Assertions.assertEquals(Set.of("menya"), map.get('m'));
        Assertions.assertEquals(Set.of("sleduet!!!", "sobaka"), map.get('s'));
        Assertions.assertEquals(Set.of("kak", "ka"), map.get('k'));
        Assertions.assertEquals(
            "[k=[kak, ka], s=[sleduet!!!, sobaka]]",
            simplePrefixMap.toString()
        );

        String[] anotherInputString = {
            "na",
            "na",
            "escho",
            "razok",
            "molorik"
        };

        for (var word : anotherInputString) {
            simplePrefixMap.insert(word);
        }

        Assertions.assertEquals(7, map.size());
        Assertions.assertEquals(Set.of("na", "nu"), map.get('n'));
        Assertions.assertEquals(Set.of("escho"), map.get('e'));
        Assertions.assertEquals(Set.of("razok"), map.get('r'));
        Assertions.assertEquals(Set.of("menya", "molorik"), map.get('m'));
        Assertions.assertEquals(
            "[k=[kak, ka], m=[molorik, menya], n=[na, nu], s=[sleduet!!!, sobaka]]",
            simplePrefixMap.toString()
        );
    }

}
